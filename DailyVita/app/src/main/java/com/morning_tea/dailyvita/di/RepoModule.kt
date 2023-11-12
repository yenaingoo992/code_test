package com.morning_tea.dailyvita.di

import com.morning_tea.dailyvita.data.repository.DiagnosisRepositoryImpl
import com.morning_tea.dailyvita.domain.repository.DiagnosisRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    @Binds
    abstract fun bindDiagnosisRepo(impl: DiagnosisRepositoryImpl): DiagnosisRepository
}