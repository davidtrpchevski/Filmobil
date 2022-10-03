package com.david.filmobil.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.david.filmobil.database.dao.FavoritesDao
import com.david.filmobil.database.dao.SearchHistoryDao
import com.david.filmobil.database.dao.WatchedMoviesDao
import com.david.filmobil.database.entities.FavoriteMovieModel
import com.david.filmobil.database.entities.SearchHistoryModel
import com.david.filmobil.database.entities.WatchedMovieModel

@Database(
    entities = [FavoriteMovieModel::class, SearchHistoryModel::class, WatchedMovieModel::class],
    version = 1,
    exportSchema = false
)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun favoritesDao(): FavoritesDao
    abstract fun searchHistoryDao(): SearchHistoryDao
    abstract fun watchedMoviesDao(): WatchedMoviesDao
}