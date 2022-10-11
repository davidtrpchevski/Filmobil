package com.david.filmobil.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.david.filmobil.database.entities.WatchlistMoviesModel

@Dao
interface WatchlistMoviesDao {
    @Query("SELECT * FROM watchlist_table")
    fun getAllWatchlistMovies(): PagingSource<Int, WatchlistMoviesModel>

    @Query("SELECT EXISTS(SELECT * FROM watchlist_table WHERE id= :watchlistMovieId)")
    suspend fun isMovieInWatchlist(watchlistMovieId: Int?): Boolean

    @Insert
    suspend fun insertMovieToWatchlist(watchlistMoviesModel: WatchlistMoviesModel)

    @Delete
    suspend fun deleteMovieFromWatchlist(watchlistMoviesModel: WatchlistMoviesModel)
}