package io.renan.kotlin.coroutines.domain.repository

import io.renan.kotlin.coroutines.data.api.MovieApiService
import io.renan.kotlin.coroutines.data.database.MovieDao
import io.renan.kotlin.coroutines.data.model.Movie
import io.renan.kotlin.coroutines.di.API_KEY
import java.lang.Exception

/**
 * Connects to the end entity, and exposes functionality to the user.
 */
class MovieRepositoryImpl(
    private val movieApiService: MovieApiService,
    private val movieDao: MovieDao
) : MovieRepository {

    override suspend fun getMovies(): List<Movie> {


        val cacheMovies = try {
            movieDao.getSavedMovies()
        }catch (e :Exception ) {
            listOf<Movie>()
        }


        val apiMovies =  try {
            movieApiService.getMovies(API_KEY)?.movies?.also {
                movieDao.saveMovies(it)
            }
        } catch (e : Exception) {
            null
        }

        return apiMovies ?: cacheMovies
    }
}