package com.david.filmobil.watchmoviecollection.watchlist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.david.filmobil.database.dao.WatchlistMoviesDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WatchlistViewModel @Inject constructor(private val watchlistMoviesDao: WatchlistMoviesDao) :
    ViewModel() {

    val watchlistMoviesData = Pager(PagingConfig(10)) {
        watchlistMoviesDao.getAllWatchlistMovies()
    }.flow.cachedIn(viewModelScope)
}