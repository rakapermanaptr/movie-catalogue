package com.example.moviecatalogue.domain.entity


data class Movie(
    val id: Int? = 0,
    val posterPath: String? = "",
    val title: String? = "",
    val backdropPath: String? = "",
    val overview: String? = ""
)