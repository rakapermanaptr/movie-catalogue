package com.example.moviecatalogue.data.source.local.entity


import com.squareup.moshi.Json

data class TvShow(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "name")
    val name: String? = "",
    @Json(name = "poster_path")
    val posterPath: String? = "",
    @Json(name = "backdrop_path")
    val backdropPath: String? = "",
    @Json(name = "overview")
    val overview: String? = ""
)