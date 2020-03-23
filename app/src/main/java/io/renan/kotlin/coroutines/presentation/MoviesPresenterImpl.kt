package io.renan.kotlin.coroutines.presentation

import io.renan.kotlin.coroutines.domain.repository.MovieRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MoviesPresenterImpl(private val movieRepository: MovieRepository) : MoviesPresenter,
    CoroutineScope {

    private val parentJob = SupervisorJob()
    private lateinit var moviesView: MoviesView

    override fun setView(moviesView: MoviesView) {
        this.moviesView = moviesView
    }

    override fun getData() {
        launch {
            val result = movieRepository.getMovies()
            if (result.value != null && result.value.isNotEmpty()) {
                moviesView.showMovies(result.value)
            } else if (result.throwable != null) {
                handleError(result.throwable)
            }
        }
    }

    override fun stop() {
        coroutineContext.cancelChildren()
    }

    private fun handleError(throwable: Throwable) {
        moviesView.showError(throwable)
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + parentJob
}