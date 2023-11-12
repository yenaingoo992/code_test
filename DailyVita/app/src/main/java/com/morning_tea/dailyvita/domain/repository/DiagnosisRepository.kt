package com.morning_tea.dailyvita.domain.repository

import com.morning_tea.dailyvita.domain.model.Allergy
import com.morning_tea.dailyvita.domain.model.Diet
import com.morning_tea.dailyvita.domain.model.HealthConcern
import kotlinx.coroutines.flow.Flow

interface DiagnosisRepository {

    suspend fun getHealthConcerns(): Flow<List<HealthConcern>>

    suspend fun getDiets(): Flow<List<Diet>>

    suspend fun getAllergies(): Flow<List<Allergy>>
}