package com.morning_tea.moviecorner.data.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("id")
    val id : Int,

    @SerializedName("title")
    val title : String,

    @SerializedName("genre_ids")
    val genreIds : List<Int>,

    @SerializedName("overview")
    val overview : String,

    @SerializedName("popularity")
    val popularity : Double,

    @SerializedName("poster_path")
    val posterPath : String?,

    @SerializedName("release_date")
    val releaseDate : String,

    @SerializedName("vote_average")
    val voteAverage : Double,

    @SerializedName("vote_count")
    val voteCount : Int
)
