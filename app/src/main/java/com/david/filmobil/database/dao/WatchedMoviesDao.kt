package com.david.filmobil.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.david.filmobil.database.entities.WatchedMovieModel
import kotlinx.coroutines.flow.Flow

@Dao
interface WatchedMoviesDao {

    @Query("SELECT * FROM watched_table")
    fun getAllWatchedMovies(): Flow<List<WatchedMovieModel>>

    @Query("SELECT EXISTS(SELECT * FROM watched_table WHERE id= :watchedMovieId)")
    suspend fun isMovieInWatched(watchedMovieId: Int?): Boolean

    @Insert
    suspend fun insertWatchedMovie(watchedMovieModel: WatchedMovieModel)

    @Delete
    suspend fun deleteWatchedMovie(watchedMovieModel: WatchedMovieModel)
}