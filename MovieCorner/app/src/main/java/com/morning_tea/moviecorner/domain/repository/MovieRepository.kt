package com.morning_tea.moviecorner.domain.repository

import androidx.paging.PagingData
import com.morning_tea.moviecorner.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getPopularMovies(): Flow<PagingData<Movie>>

    suspend fun getUpcomingMovies(): Flow<PagingData<Movie>>

    suspend fun saveFavouriteMovie(movie: Movie)
}