package com.david.filmobil.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.david.filmobil.database.entities.FavoriteMovieModel

@Dao
interface FavoritesDao {

    @Query("SELECT * FROM favorite_table ORDER BY insert_date desc ")
    fun getAllFavoriteMovies(): PagingSource<Int, FavoriteMovieModel>

    @Query("SELECT EXISTS(SELECT * FROM favorite_table WHERE id= :favoriteMovieId)")
    suspend fun isMovieInFavorites(favoriteMovieId: Int): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteMovie(favoriteMovie: FavoriteMovieModel)

    @Delete
    suspend fun deleteFavoriteMovie(favoriteMovie: FavoriteMovieModel)
}