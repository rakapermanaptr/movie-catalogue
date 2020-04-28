package com.example.moviecatalogue.data.source.local.entity


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "moviesentities")
@Parcelize
data class Movie(
    @PrimaryKey
    @Json(name = "id")
    val id: Int? = 0,
    @Json(name = "poster_path")
    val posterPath: String? = "",
    @Json(name = "title")
    val title: String? = "",
    @Json(name = "backdrop_path")
    val backdropPath: String? = "",
    @Json(name = "overview")
    val overview: String? = "",
    var isFavorite: Boolean = false
): Parcelable