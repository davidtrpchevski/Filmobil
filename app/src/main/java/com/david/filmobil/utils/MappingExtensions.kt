package com.david.filmobil.utils

import com.david.filmobil.database.entities.FavoriteMovieModel
import com.david.filmobil.database.entities.WatchedMovieModel
import com.david.filmobil.database.entities.WatchlistMoviesModel
import com.david.filmobil.details.model.MovieDetailsModel

fun MovieDetailsModel.mapToMovieDbModel(): FavoriteMovieModel = FavoriteMovieModel(
    id = id,
    adult = adult,
    backdropPath = backdropPath,
//        genreIds = genreIds,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    releaseDate = releaseDate,
    title = title,
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount,
    insertDate = System.currentTimeMillis(),
    homepage = homepage,
    imdbId = imdbId
)

fun MovieDetailsModel.mapToWatchedMovieModel(): WatchedMovieModel = WatchedMovieModel(
    id = id,
    backdropPath = backdropPath,
//        genreIds = genreIds,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    releaseDate = releaseDate,
    title = title,
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount,
    insertDate = System.currentTimeMillis(),
    homepage = homepage,
    imdbId = imdbId
)

fun MovieDetailsModel.mapToWatchlistModel(): WatchlistMoviesModel = WatchlistMoviesModel(
    id = id,
    backdropPath = backdropPath,
//        genreIds = genreIds,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    releaseDate = releaseDate,
    title = title,
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount,
    insertDate = System.currentTimeMillis(),
    homepage = homepage,
    imdbId = imdbId
)