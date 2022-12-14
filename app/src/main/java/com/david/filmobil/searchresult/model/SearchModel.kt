package com.david.filmobil.searchresult.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchModel(
    @Json(name = "page")
    val page: Int?,
    @Json(name = "results")
    val searchResultModels: List<SearchResultModel?>?,
    @Json(name = "total_pages")
    val totalPages: Int?,
    @Json(name = "total_results")
    val totalResults: Int?
)