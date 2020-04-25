package com.example.moviecatalogue.domain.entity


import com.squareup.moshi.Json

data class TvShow(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "name")
    val name: String? = "",
    @Json(name = "poster_path")
    val posterPath: String? = ""
)