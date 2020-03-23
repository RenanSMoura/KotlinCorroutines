package io.renan.kotlin.coroutines.domain.repository

import io.renan.kotlin.coroutines.data.model.Movie
import io.renan.kotlin.coroutines.data.model.Result

/**
 * Interface used to communicate to the end entities, when fetching data.
 */
interface MovieRepository {

    suspend fun getMovies(): Result<List<Movie>>
}