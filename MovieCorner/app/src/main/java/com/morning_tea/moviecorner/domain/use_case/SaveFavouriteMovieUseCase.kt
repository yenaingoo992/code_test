package com.morning_tea.moviecorner.domain.use_case

import com.morning_tea.moviecorner.domain.model.Movie
import com.morning_tea.moviecorner.domain.repository.MovieRepository
import javax.inject.Inject

class SaveFavouriteMovieUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    suspend operator fun invoke(movie: Movie) {
        repository.saveFavouriteMovie(movie)
    }
}