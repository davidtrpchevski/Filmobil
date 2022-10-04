package com.david.filmobil.di

import android.content.Context
import androidx.room.Room
import com.david.filmobil.constants.MOVIE_DB_NAME
import com.david.filmobil.database.dao.FavoritesDao
import com.david.filmobil.database.dao.SearchHistoryDao
import com.david.filmobil.database.dao.WatchedMoviesDao
import com.david.filmobil.database.dao.WatchlistMoviesDao
import com.david.filmobil.database.database.MoviesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideMoviesDatabase(@ApplicationContext context: Context): MoviesDatabase =
        Room.databaseBuilder(context, MoviesDatabase::class.java, MOVIE_DB_NAME).build()

    @Provides
    @Singleton
    fun provideMovieDBDao(moviesDatabase: MoviesDatabase): FavoritesDao =
        moviesDatabase.favoritesDao()

    @Provides
    @Singleton
    fun provideSearchHistoryDao(moviesDatabase: MoviesDatabase): SearchHistoryDao =
        moviesDatabase.searchHistoryDao()

    @Provides
    @Singleton
    fun provideWatchedMoviesDao(moviesDatabase: MoviesDatabase): WatchedMoviesDao =
        moviesDatabase.watchedMoviesDao()

    @Provides
    @Singleton
    fun provideWatchlistMoviesDao(moviesDatabase: MoviesDatabase): WatchlistMoviesDao =
        moviesDatabase.watchlistMoviesDao()
}