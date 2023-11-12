package com.morning_tea.dailyvita.data.repository

import com.morning_tea.dailyvita.data.DiagnosisDataSource
import com.morning_tea.dailyvita.data.Response
import com.morning_tea.dailyvita.data.fromJson
import com.morning_tea.dailyvita.domain.model.Allergy
import com.morning_tea.dailyvita.domain.model.Diet
import com.morning_tea.dailyvita.domain.model.HealthConcern
import com.morning_tea.dailyvita.domain.repository.DiagnosisRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DiagnosisRepositoryImpl @Inject constructor(
    private val dataSource: DiagnosisDataSource
): DiagnosisRepository {

    override suspend fun getHealthConcerns(): Flow<List<HealthConcern>> {
        return flow {
            val response = dataSource.readJsonFromAssets("health_concern.json").fromJson<Response<HealthConcern>>()
            emit(response.data)
        }
    }

    override suspend fun getDiets(): Flow<List<Diet>> {
        return flow {
            val diets = mutableListOf<Diet>()
            val none = Diet(0, "None", "")
            none.isChecked = true
            diets.add(none)
            val response = dataSource.readJsonFromAssets("diets.json").fromJson<Response<Diet>>()
            diets.addAll(response.data)
            emit(diets.toList())
        }
    }

    override suspend fun getAllergies(): Flow<List<Allergy>> {
        return flow {
            val response = dataSource.readJsonFromAssets("allergies.json").fromJson<Response<Allergy>>()
            emit(response.data)
        }
    }
}