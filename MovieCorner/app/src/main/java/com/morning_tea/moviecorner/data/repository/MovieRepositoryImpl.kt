package com.morning_tea.moviecorner.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.morning_tea.moviecorner.data.cache.dao.MovieDao
import com.morning_tea.moviecorner.data.dataSource.PopularMovieDataSource
import com.morning_tea.moviecorner.data.dataSource.UpcomingMovieDataSource
import com.morning_tea.moviecorner.data.toEntity
import com.morning_tea.moviecorner.domain.model.Movie
import com.morning_tea.moviecorner.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val popularDataSource: PopularMovieDataSource,
    private val upcomingDataSource: UpcomingMovieDataSource,
    private val dao: MovieDao
) : MovieRepository {

    override suspend fun getPopularMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                popularDataSource
            }
        ).flow
    }

    override suspend fun getUpcomingMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                upcomingDataSource
            }
        ).flow
    }

    override suspend fun saveFavouriteMovie(movie: Movie) {
        dao.updateMovie(movie.toEntity())
    }
}