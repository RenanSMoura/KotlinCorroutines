package io.renan.kotlin.coroutines.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
class Movie(
    @PrimaryKey val id: Int,
    val title: String,
    @SerializedName("poster_path") val posterPath: String
)