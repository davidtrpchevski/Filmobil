package com.david.filmobil.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.david.filmobil.common.API_PAGING_STARTING_SIZE
import com.david.filmobil.network.RemoteService
import com.david.filmobil.paging.HomePagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val remoteService: RemoteService) : ViewModel() {

    val moviesList = Pager(
        config = PagingConfig(API_PAGING_STARTING_SIZE),
        pagingSourceFactory = { HomePagingSource(remoteService) }).flow.cachedIn(viewModelScope)
}