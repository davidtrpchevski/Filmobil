package com.david.filmobil.watchmoviecollection.watched.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.david.filmobil.constants.LOCAL_DATABASE_PAGING_SIZE
import com.david.filmobil.database.dao.WatchedMoviesDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WatchedViewModel @Inject constructor(private val watchedMoviesDao: WatchedMoviesDao) :
    ViewModel() {

    val watchedMoviesData = Pager(PagingConfig(LOCAL_DATABASE_PAGING_SIZE)) {
        watchedMoviesDao.getAllWatchedMovies()
    }.flow.cachedIn(viewModelScope)
}