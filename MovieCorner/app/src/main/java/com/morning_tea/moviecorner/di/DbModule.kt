package com.morning_tea.moviecorner.di

import android.content.Context
import com.morning_tea.moviecorner.data.cache.MovieDB
import com.morning_tea.moviecorner.data.cache.dao.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    @Singleton
    fun provideDB(@ApplicationContext context: Context): MovieDB = MovieDB.getInstance(context)

    @Provides
    @Singleton
    fun provideMovieDao(db: MovieDB): MovieDao = db.getMovieDao()
}