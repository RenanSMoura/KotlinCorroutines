package io.renan.kotlin.coroutines.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.renan.kotlin.coroutines.data.model.Movie

/**
 * DAO Used to communicate operations for saving, deleting and editing movies in the database.
 */

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovies(movies: List<Movie>)

    @Query("SELECT * FROM movies")
    suspend fun getSavedMovies(): List<Movie>
}