package com.morning_tea.moviecorner.data

import com.morning_tea.moviecorner.data.cache.entity.MovieEntity
import com.morning_tea.moviecorner.domain.model.Movie

fun Movie.toEntity() = MovieEntity(
    id = this.id,
    overview = this.overview,
    popularity = this.popularity,
    posterPath = this.posterPath,
    releaseDate = this.releaseDate,
    title = this.title,
    voteAverage = this.voteAverage,
    voteCount = this.voteCount,
    isFavourite = this.isFavourite,
    type = this.type
)

fun List<MovieEntity>.toModel() : List<Movie> {
    return this.map {
        it.toModel()
    }
}

fun MovieEntity.toModel(): Movie {
    val movie = Movie(
        id = this.id,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        title = this.title,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount,
        type = this.type
    )
    movie.isFavourite = this.isFavourite
    return movie
}