package com.morning_tea.moviecorner.domain.model

import android.os.Parcelable
import com.morning_tea.moviecorner.BuildConfig
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id : Int,
    val genreIds : List<Int> = listOf(),
    val overview : String,
    val popularity : Double,
    val posterPath : String,
    val releaseDate : String,
    val title : String,
    val voteAverage : Double,
    val voteCount : Int,
    val type: String
): Parcelable {
    val imageUrl : String get() = buildString {
        append(BuildConfig.IMAGE_URL)
        append("t/p/w500")
        append(posterPath)
    }

    @Transient
    var isFavourite: Boolean = false
}