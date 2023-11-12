package com.morning_tea.moviecorner.data.cache.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "overview")
    val overview : String,

    @ColumnInfo(name = "popularity")
    val popularity : Double,

    @ColumnInfo(name = "posterPath")
    val posterPath : String,

    @ColumnInfo(name = "releaseDate")
    val releaseDate : String,

    @ColumnInfo(name = "title")
    val title : String,

    @ColumnInfo(name = "voteAverage")
    val voteAverage : Double,

    @ColumnInfo(name = "voteCount")
    val voteCount : Int,

    @ColumnInfo(name = "isFavourite")
    val isFavourite : Boolean,

    @ColumnInfo(name = "type")
    val type : String
)