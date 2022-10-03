package com.david.filmobil.home.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResultModel(
    @Json(name = "page")
    val page: Int?,
    @Json(name = "results")
    val movieModels: List<MovieModel>?,
    @Json(name = "total_pages")
    val totalPages: Int?,
    @Json(name = "total_results")
    val totalResults: Int?
)