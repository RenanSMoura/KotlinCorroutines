package io.renan.kotlin.coroutines.presentation

interface MoviesPresenter {

  fun setView(moviesView: MoviesView)

  fun getData()
}