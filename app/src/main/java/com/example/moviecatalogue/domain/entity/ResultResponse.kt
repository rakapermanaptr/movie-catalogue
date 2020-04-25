package com.example.moviecatalogue.domain.entity



data class ResultResponse<T>(
    val page: Int,
    val results: List<T>,
    val totalPages: Int,
    val totalResults: Int
)