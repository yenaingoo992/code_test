package com.morning_tea.moviecorner.data

import com.morning_tea.moviecorner.data.cache.entity.MovieEntity
import com.morning_tea.moviecorner.data.remote.response.MovieResponse
import com.morning_tea.moviecorner.data.remote.response.MoviesResponse
import com.morning_tea.moviecorner.domain.model.Movie

fun MoviesResponse.toModel(type: String) : List<Movie> {
    return this.results.map {
        it.toModel(type)
    }
}

fun MovieResponse.toModel(type: String): Movie = Movie(
    id = this.id,
    genreIds = this.genreIds,
    overview = this.overview,
    popularity = this.popularity,
    posterPath = this.posterPath ?: "",
    releaseDate = this.releaseDate,
    title = this.title,
    voteAverage = this.voteAverage,
    voteCount = this.voteCount,
    type = type
)

fun MoviesResponse.toEntity(type: String) : List<MovieEntity> {
    return this.results.map {
        it.toEntity(type)
    }
}

fun MovieResponse.toEntity(type: String): MovieEntity = MovieEntity(
    id = this.id,
    overview = this.overview,
    popularity = this.popularity,
    posterPath = this.posterPath ?: "",
    releaseDate = this.releaseDate,
    title = this.title,
    voteAverage = this.voteAverage,
    voteCount = this.voteCount,
    isFavourite = false,
    type = type
)