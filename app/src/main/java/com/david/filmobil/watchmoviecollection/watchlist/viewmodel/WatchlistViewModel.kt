package com.david.filmobil.watchmoviecollection.watchlist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.david.filmobil.constants.LOCAL_DATABASE_PAGING_SIZE
import com.david.filmobil.database.dao.WatchlistMoviesDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WatchlistViewModel @Inject constructor(private val watchlistMoviesDao: WatchlistMoviesDao) :
    ViewModel() {

    val watchlistMoviesData = Pager(PagingConfig(LOCAL_DATABASE_PAGING_SIZE)) {
        watchlistMoviesDao.getAllWatchlistMovies()
    }.flow.cachedIn(viewModelScope)
}