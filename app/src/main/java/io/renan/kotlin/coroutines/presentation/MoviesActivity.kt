package io.renan.kotlin.coroutines.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import io.renan.kotlin.coroutines.R
import io.renan.kotlin.coroutines.data.model.Movie
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MoviesActivity : AppCompatActivity(), MoviesView {


    private val presenter by inject<MoviesPresenter>()
    private val movieAdapter by lazy { MovieAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUi()

        presenter.setView(this)
    }

    private fun initUi() {
        moviesList.adapter = movieAdapter
        moviesList.layoutManager = LinearLayoutManager(this)
        swipeToRefresh.setOnRefreshListener { presenter.getData() }
    }

    override fun showMovies(movies: List<Movie>) {
        movieAdapter.setData(movies)
        swipeToRefresh.isRefreshing = false
    }

    override fun showError(throwable: Throwable) {
        swipeToRefresh.isRefreshing = false
        // handle error
    }

    override fun onStop() {
        presenter.stop()
        super.onStop()
    }

    override fun onStart() {
        super.onStart()
        presenter.getData()
    }
}