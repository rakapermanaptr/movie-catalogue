package com.example.moviecatalogue.domain.entity

import com.squareup.moshi.Json

data class TvShowDetail(
    @Json(name = "backdrop_path")
    val backdropPath: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "overview")
    val overview: String
)