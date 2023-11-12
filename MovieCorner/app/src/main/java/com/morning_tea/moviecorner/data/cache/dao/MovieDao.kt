package com.morning_tea.moviecorner.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.morning_tea.moviecorner.data.cache.entity.MovieEntity

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(users: List<MovieEntity>)

    @Query("SELECT * FROM movie WHERE type = 'popular'")
    suspend fun getPopularMovies(): List<MovieEntity>

    @Query("SELECT * FROM movie WHERE type = 'upcoming'")
    suspend fun getUpcomingMovies(): List<MovieEntity>

    @Query("DELETE FROM movie")
    suspend fun clearAll()

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateMovie(movieEntity: MovieEntity)

    @Query("SELECT movie.id FROM movie WHERE movie.isFavourite = 1")
    suspend fun getFavMovieIds(): List<Int>

}