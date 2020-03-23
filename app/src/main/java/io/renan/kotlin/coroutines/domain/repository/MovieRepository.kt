package io.renan.kotlin.coroutines.domain.repository

import io.renan.kotlin.coroutines.data.model.Movie

/**
 * Interface used to communicate to the end entities, when fetching data.
 */
interface MovieRepository {

    fun getMovies(
        onMoviesReceived: (List<Movie>) -> Unit,
        onError: (Throwable) -> Unit
    )
}