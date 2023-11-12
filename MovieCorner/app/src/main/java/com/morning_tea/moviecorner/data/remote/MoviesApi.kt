package com.morning_tea.moviecorner.data.remote

import com.morning_tea.moviecorner.BuildConfig
import com.morning_tea.moviecorner.data.remote.response.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET("3/movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") key: String = BuildConfig.API_KEY,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int
    ): Response<MoviesResponse>

    @GET("3/movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") key: String = BuildConfig.API_KEY,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int
    ): Response<MoviesResponse>
}