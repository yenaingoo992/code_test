package com.morning_tea.moviecorner.data.dataSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.morning_tea.moviecorner.data.cache.dao.MovieDao
import com.morning_tea.moviecorner.data.fromJson
import com.morning_tea.moviecorner.data.remote.MoviesApi
import com.morning_tea.moviecorner.data.remote.response.ErrorResponse
import com.morning_tea.moviecorner.data.toEntity
import com.morning_tea.moviecorner.data.toModel
import com.morning_tea.moviecorner.domain.model.Movie
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PopularMovieDataSource @Inject constructor(
    private val api: MoviesApi,
    private val dao: MovieDao
) : PagingSource<Int, Movie>() {

    companion object {
        private const val STARTING_PAGE = 1
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1) ?: state.closestPageToPosition(
                position
            )?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        var position = params.key ?: STARTING_PAGE
        return try {
            val response = api.getPopularMovies(page = position)
            val data = response.body()
            if (response.isSuccessful && data != null) {
                if (position <= data.totalPages) {
                    position += 1
                }
                dao.insertAll(data.toEntity("popular"))
                val favIds = dao.getFavMovieIds()
                LoadResult.Page(data = data.toModel("popular").map {
                    it.isFavourite = favIds.contains(it.id)
                    it
                }, prevKey = null, nextKey = position)
            } else {
                val errorResponse = response.errorBody()?.string()?.fromJson<ErrorResponse>()
                LoadResult.Error(Throwable(message = errorResponse?.message))
            }
        } catch (e: Exception) {
            val cachedMovies = dao.getPopularMovies()
            if (cachedMovies.isEmpty()) {
                LoadResult.Error(e)
            } else {
                LoadResult.Page(data = cachedMovies.toModel().sortedByDescending { it.id }, prevKey = null, nextKey = null)
            }
        }
    }
}