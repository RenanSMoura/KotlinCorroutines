package io.renan.kotlin.coroutines.data.model

import com.google.gson.annotations.SerializedName

/**
 * Wrapper for movies response list.
 */
class MoviesResponse(
    @SerializedName("results") val movies: List<Movie>
)