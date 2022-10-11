package com.david.filmobil.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.david.filmobil.database.entities.WatchedMovieModel

@Dao
interface WatchedMoviesDao {

    @Query("SELECT * FROM watched_table")
    fun getAllWatchedMovies(): PagingSource<Int, WatchedMovieModel>

    @Query("SELECT EXISTS(SELECT * FROM watched_table WHERE id= :watchedMovieId)")
    suspend fun isMovieInWatched(watchedMovieId: Int?): Boolean

    @Insert
    suspend fun insertWatchedMovie(watchedMovieModel: WatchedMovieModel)

    @Delete
    suspend fun deleteWatchedMovie(watchedMovieModel: WatchedMovieModel)
}