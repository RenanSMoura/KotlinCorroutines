package io.renan.kotlin.coroutines.presentation

import io.renan.kotlin.coroutines.data.model.Movie

interface MoviesView {

    fun showMovies(movies: List<Movie>)

    fun showError(throwable: Throwable)
}