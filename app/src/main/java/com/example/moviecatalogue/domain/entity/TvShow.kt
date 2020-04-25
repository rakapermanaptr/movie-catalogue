package com.example.moviecatalogue.domain.entity



data class TvShow(
    val id: Int?,
    val name: String? = "",
    val posterPath: String? = "",
    val backdropPath: String? = "",
    val overview: String? = ""
)