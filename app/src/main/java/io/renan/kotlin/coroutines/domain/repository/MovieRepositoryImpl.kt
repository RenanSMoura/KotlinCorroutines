package io.renan.kotlin.coroutines.domain.repository

import io.renan.kotlin.coroutines.data.api.MovieApiService
import io.renan.kotlin.coroutines.data.database.MovieDao
import io.renan.kotlin.coroutines.data.model.Movie
import io.renan.kotlin.coroutines.data.model.Result
import io.renan.kotlin.coroutines.di.API_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import java.io.IOException

/**
 * Connects to the end entity, and exposes functionality to the user.
 */
class MovieRepositoryImpl(
    private val movieApiService: MovieApiService,
    private val movieDao: MovieDao
) : MovieRepository {

    override suspend fun getMovies(): Result<List<Movie>> = withContext(Dispatchers.IO) {

        val cacheMoviesDeferred = async { movieDao.getSavedMovies() }
        val resultDeferred = async { movieApiService.getMovies(API_KEY).execute() }

        val cacheMovies = cacheMoviesDeferred.await()
        try {
            val result = resultDeferred.await()
            val moviesResponse = result.body()?.movies
            if (result.isSuccessful && moviesResponse != null) {
                Result(moviesResponse, null)
            } else {
                Result(cacheMovies, null)
            }
        } catch (error: Throwable) {
            if (error is IOException && cacheMovies.isNotEmpty()) {
                Result(cacheMovies, null)
            } else {
                Result(null, error)
            }
        }
    }
}