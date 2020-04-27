package com.example.moviecatalogue.data.source.local.entity


import com.squareup.moshi.Json

data class ResultResponse<T>(
    @Json(name = "page")
    val page: Int,
    @Json(name = "results")
    val results: List<T>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int
)