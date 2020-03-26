package io.renan.kotlin.coroutines.presentation

import io.renan.kotlin.coroutines.domain.repository.MovieRepository
import io.renan.kotlin.coroutines.utils.logCoroutines
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MoviesPresenterImpl(private val movieRepository: MovieRepository) : MoviesPresenter,
    CoroutineScope {

    private val coroutineExceptionHandler =
        CoroutineExceptionHandler { coroutineContext, throwable ->

        }
    private val parentJob = SupervisorJob()
    private lateinit var moviesView: MoviesView

    override fun setView(moviesView: MoviesView) {
        this.moviesView = moviesView
    }

    override fun getData() {
        launch {
            logCoroutines("getData", coroutineContext)
            val result = runCatching { movieRepository.getMovies() }

            result.onSuccess {
                moviesView.showMovies(it)
            }.onFailure {
                handleError(it)
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
        get() = Dispatchers.Main + parentJob + coroutineExceptionHandler
}