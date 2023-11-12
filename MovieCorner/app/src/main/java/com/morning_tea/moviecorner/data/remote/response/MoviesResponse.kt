package com.morning_tea.moviecorner.data.remote.response

import com.google.gson.annotations.SerializedName

data class MoviesResponse(

    @SerializedName("results")
    val results : List<MovieResponse>,

    @SerializedName("page")
    val page: Int,

    @SerializedName("total_pages")
    val totalPages: Int
)