package com.david.filmobil.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.david.filmobil.home.model.MovieModel
import com.david.filmobil.network.RemoteService
import com.david.filmobil.network.result.ApiResult
import com.david.filmobil.network.result.unpackResult

class HomePagingSource(private val remoteService: RemoteService) :
    PagingSource<Int, MovieModel>() {

    override fun getRefreshKey(state: PagingState<Int, MovieModel>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        val pageNumber = params.key ?: 1
        return try {
            when (val response = unpackResult { remoteService.getMoviesList(pageNumber) }) {
                is ApiResult.Success -> {
                    val result = response.data
                    LoadResult.Page(
                        data = result.movieModels ?: emptyList(),
                        prevKey = if (result.page == pageNumber) null else pageNumber - 1,
                        nextKey = if (result.totalPages == pageNumber) null else pageNumber + 1
                    )
                }
                is ApiResult.Error -> {
                    LoadResult.Error(response.throwable)
                }
                else -> {
                    LoadResult.Error(Throwable("Unknown error"))
                }
            }
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}