package com.morning_tea.moviecorner.data.cache

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.morning_tea.moviecorner.data.cache.dao.MovieDao
import com.morning_tea.moviecorner.data.cache.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class MovieDB : RoomDatabase() {

    abstract fun getMovieDao(): MovieDao

    companion object {
        private const val DB_NAME = "movie_db"

        @Volatile
        private var INSTANCE : MovieDB? = null

        fun getInstance(context: Context): MovieDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    MovieDB::class.java,
                    DB_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}