package com.example.moviecatalogue.domain.entity


import com.squareup.moshi.Json

data class Movie(
    @Json(name = "id")
    val id: Int? = 0,
    @Json(name = "poster_path")
    val posterPath: String? = "",
    @Json(name = "title")
    val title: String? = ""
)