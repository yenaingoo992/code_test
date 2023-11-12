package com.morning_tea.dailyvita.domain.usecase

import com.morning_tea.dailyvita.domain.model.HealthConcern
import com.morning_tea.dailyvita.domain.repository.DiagnosisRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHealthConcernsUseCase @Inject constructor(
    private val repository: DiagnosisRepository
) {

    suspend operator fun invoke(): Flow<List<HealthConcern>> {
        return repository.getHealthConcerns()
    }
}