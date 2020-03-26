package io.renan.kotlin.coroutines.domain.repository

import io.renan.kotlin.coroutines.contexProvider.CoroutineContextProvider
import io.renan.kotlin.coroutines.data.api.MovieApiService
import io.renan.kotlin.coroutines.data.database.MovieDao
import io.renan.kotlin.coroutines.data.model.Movie
import io.renan.kotlin.coroutines.data.model.Result
import io.renan.kotlin.coroutines.di.API_KEY
import io.renan.kotlin.coroutines.utils.logCoroutines
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import java.io.IOException

/**
 * Connects to the end entity, and exposes functionality to the user.
 */
class MovieRepositoryImpl(
    private val movieApiService: MovieApiService,
    private val movieDao: MovieDao,
    private val contextProvider: CoroutineContextProvider
) : MovieRepository {

    override suspend fun getMovies(): List<Movie> = withContext(contextProvider.context()) {

        logCoroutines("getMovies", coroutineContext)

        val cacheMoviesDeferred = async {
            logCoroutines("getSavedMovies", coroutineContext)
            movieDao.getSavedMovies()
        }
        val cacheMovies = cacheMoviesDeferred.await()
        val apiMovies = movieApiService.getMovies(API_KEY)?.movies

        apiMovies ?: cacheMovies
    }
}