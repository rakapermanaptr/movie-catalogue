package com.example.moviecatalogue.domain.entity


import com.squareup.moshi.Json

data class MovieDetail(
    @Json(name = "backdrop_path")
    val backdropPath: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "overview")
    val overview: String,
    @Json(name = "title")
    val title: String
)