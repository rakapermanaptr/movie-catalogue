package com.example.moviecatalogue.data.source.local.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "tvshowentities")
data class TvShow(
    @PrimaryKey
    @Json(name = "id")
    val id: Int?,
    @Json(name = "name")
    val name: String? = "",
    @Json(name = "poster_path")
    val posterPath: String? = "",
    @Json(name = "backdrop_path")
    val backdropPath: String? = "",
    @Json(name = "overview")
    val overview: String? = "",
    var isFavorite: Boolean = false
)