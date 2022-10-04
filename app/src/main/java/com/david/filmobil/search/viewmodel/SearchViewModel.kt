package com.david.filmobil.search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.david.filmobil.database.dao.SearchHistoryDao
import com.david.filmobil.database.entities.SearchHistoryModel
import com.david.filmobil.di.dispatchers.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchHistoryDao: SearchHistoryDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _searchHistoryData = MutableStateFlow<List<SearchHistoryModel>>(emptyList())
    val searchHistoryData = _searchHistoryData.asStateFlow()

    init {
        getAllSearchHistory()
    }

    private fun getAllSearchHistory() {
        viewModelScope.launch(ioDispatcher) {
            _searchHistoryData.value = searchHistoryDao.getAllSearchHistory()
        }
    }

    fun insertIntoSearchHistory(searchQuery: String) {
        val searchHistoryModel = SearchHistoryModel(
            searchHistoryQuery = searchQuery,
            searchHistoryInsertionDate = System.currentTimeMillis()
        )
        viewModelScope.launch {
            searchHistoryDao.insertIntoSearchHistory(searchHistoryModel)
        }
    }

    fun filterSearchHistory(searchQuery: String?) {
        viewModelScope.launch(ioDispatcher) {
            _searchHistoryData.value = searchHistoryDao.getAllSearchHistory(searchQuery)
        }
    }

    fun removeFromSearchHistory(searchHistoryModel: SearchHistoryModel) {
        viewModelScope.launch(ioDispatcher) {
            searchHistoryDao.deleteFromSearchHistory(searchHistoryModel)
            _searchHistoryData.value = searchHistoryDao.getAllSearchHistory()
        }
    }
}