package io.renan.kotlin.coroutines.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.renan.kotlin.coroutines.R
import io.renan.kotlin.coroutines.data.model.Movie
import io.renan.kotlin.coroutines.di.MOVIE_IMAGE_BASE_PATH
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter : RecyclerView.Adapter<MovieViewHolder>() {

    private val items = mutableListOf<Movie>()

    fun setData(newItems: List<Movie>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.showData(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)

        return MovieViewHolder(view)
    }
}

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun showData(movie: Movie) = with(itemView) {
        Glide.with(this)
            .load(MOVIE_IMAGE_BASE_PATH + movie.posterPath)
            .into(movieImage)
    }
}