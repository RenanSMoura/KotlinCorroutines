package io.renan.kotlin.coroutines.presentation

import io.renan.kotlin.coroutines.domain.repository.MovieRepository

class MoviesPresenterImpl(private val movieRepository: MovieRepository) : MoviesPresenter {

    private lateinit var moviesView: MoviesView

    override fun setView(moviesView: MoviesView) {
        this.moviesView = moviesView
    }

    override fun getData() {
        movieRepository.getMovies(
            onMoviesReceived = { movies -> moviesView.showMovies(movies) },
            onError = { throwable -> handleError(throwable) }
        )
    }

    private fun handleError(throwable: Throwable) {
        moviesView.showError(throwable)
    }
}