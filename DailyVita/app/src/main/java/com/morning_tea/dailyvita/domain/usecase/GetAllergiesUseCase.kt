package com.morning_tea.dailyvita.domain.usecase

import com.morning_tea.dailyvita.domain.model.Allergy
import com.morning_tea.dailyvita.domain.repository.DiagnosisRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllergiesUseCase @Inject constructor(
    private val repository: DiagnosisRepository
) {

    suspend operator fun invoke(): Flow<List<Allergy>> {
        return repository.getAllergies()
    }
}