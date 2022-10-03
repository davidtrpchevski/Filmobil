package com.david.filmobil.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.david.filmobil.database.entities.WatchedMovieModel

@Dao
interface WatchedMoviesDao {

    @Query("SELECT * FROM watched_table")
    suspend fun getAllWatchedMovies(): List<WatchedMovieModel>

    @Insert
    suspend fun insertWatchedMovie(watchedMovieModel: WatchedMovieModel)

    @Delete
    suspend fun deleteWatchedMovie(watchedMovieModel: WatchedMovieModel)
}