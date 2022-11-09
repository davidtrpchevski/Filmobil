package com.david.filmobil.network

import com.david.filmobil.details.model.MovieDetailsModel
import com.david.filmobil.home.model.ResultModel
import com.david.filmobil.network.model.TrendingMediaType
import com.david.filmobil.network.model.TrendingTimeWindow
import com.david.filmobil.searchresult.model.SearchModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RemoteService {

    private companion object {
        private const val DISCOVER_KEY = "discover"
        private const val MOVIE_KEY = "movie"
        private const val POPULAR_KEY = "popular"
        private const val TOP_RATED_KEY = "top_rated"
        private const val TRENDING_KEY = "trending"
        private const val TRENDING_TIME_WINDOW_KEY = "timeWindowKey"
        private const val MEDIA_TYPE_KEY = "mediaTypeKey"
        private const val PAGE_KEY = "page"
        private const val MOVIE_ID = "movieId"
        private const val SEARCH_KEY = "search"
        private const val SEARCH_QUERY = "query"
    }

    @GET("$DISCOVER_KEY/$MOVIE_KEY")
    suspend fun getMoviesList(
        @Query(PAGE_KEY) page: Int? = 1
    ): Response<ResultModel>

    @GET("$MOVIE_KEY/{$MOVIE_ID}")
    suspend fun getMovie(
        @Path(MOVIE_ID) movieId: Int,
    ): Response<MovieDetailsModel>

    @GET("$SEARCH_KEY/$MOVIE_KEY")
    suspend fun getMoviesByTitle(
        @Query(SEARCH_QUERY) movieSearchQuery: String,
        @Query(PAGE_KEY) page: Int? = 1,
    ): Response<SearchModel>

    @GET("$MOVIE_KEY/$POPULAR_KEY")
    suspend fun getPopularMovies(
        @Query(PAGE_KEY) page: Int? = 1,
    ): Response<ResultModel>

    @GET("$MOVIE_KEY/$TOP_RATED_KEY")
    suspend fun getTopRatedMovies(
        @Query(PAGE_KEY) page: Int? = 1,
    ): Response<ResultModel>

    @GET("$TRENDING_KEY/{$MEDIA_TYPE_KEY}/{$TRENDING_TIME_WINDOW_KEY}")
    suspend fun getTrendingMovies(
        @Path(TRENDING_TIME_WINDOW_KEY) timeWindow: String? = TrendingTimeWindow.DAY_TIME_WINDOW.timeWindow,
        @Path(MEDIA_TYPE_KEY) mediaType: String? = TrendingMediaType.MOVIE_MEDIA_TYPE.mediaType,
        @Query(PAGE_KEY) page: Int? = 1,
    ): Response<ResultModel>
}