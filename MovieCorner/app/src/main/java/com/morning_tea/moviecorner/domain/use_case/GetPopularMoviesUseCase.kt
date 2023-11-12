package com.morning_tea.moviecorner.domain.use_case

import androidx.paging.PagingData
import com.morning_tea.moviecorner.domain.model.Movie
import com.morning_tea.moviecorner.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    suspend operator fun invoke(): Flow<PagingData<Movie>> {
        return repository.getPopularMovies()
    }
}