package com.david.filmobil.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.david.filmobil.database.entities.WatchlistMoviesModel

@Dao
interface WatchlistMoviesDao {
    @Query("SELECT * FROM watchlist_table")
    suspend fun getAllWatchlistMovies(): List<WatchlistMoviesModel>

    @Insert
    suspend fun insertMovieToWatchlist(watchlistMoviesModel: WatchlistMoviesModel)

    @Delete
    suspend fun deleteMovieFromWatchlist(watchlistMoviesModel: WatchlistMoviesModel)
}