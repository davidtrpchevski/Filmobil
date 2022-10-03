package com.david.filmobil.network

import com.david.filmobil.BuildConfig
import com.david.filmobil.details.model.MovieDetailsModel
import com.david.filmobil.home.model.ResultModel
import com.david.filmobil.searchresult.model.SearchModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RemoteService {

    private companion object {
        private const val DISCOVER_KEY = "discover"
        private const val MOVIE_KEY = "movie"
        private const val API_KEY = "api_key"
        private const val PAGE_KEY = "page"
        private const val MOVIE_ID = "movieId"
        private const val SEARCH_KEY = "search"
        private const val SEARCH_QUERY = "query"
    }

    @GET("${BuildConfig.API_VERSION}/$DISCOVER_KEY/$MOVIE_KEY?$API_KEY=${BuildConfig.API_KEY}")
    suspend fun getMoviesList(
        @Query(PAGE_KEY) page: Int? = 1
    ): Response<ResultModel>

    @GET("${BuildConfig.API_VERSION}/$MOVIE_KEY/{$MOVIE_ID}?$API_KEY=${BuildConfig.API_KEY}")
    suspend fun getMovie(
        @Path(MOVIE_ID) movieId: Int
    ): Response<MovieDetailsModel>

    @GET("${BuildConfig.API_VERSION}/$SEARCH_KEY/$MOVIE_KEY?$API_KEY=${BuildConfig.API_KEY}")
    suspend fun getMoviesByTitle(
        @Query(SEARCH_QUERY) movieSearchQuery: String,
        @Query(PAGE_KEY) page: Int? = 1
    ): Response<SearchModel>
}