package com.morning_tea.dailyvita.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.morning_tea.dailyvita.domain.model.Allergy
import com.morning_tea.dailyvita.domain.model.Diet
import com.morning_tea.dailyvita.domain.model.HealthConcern
import com.morning_tea.dailyvita.domain.model.VitaminResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableLiveData<OnboardingState>()
    val state: LiveData<OnboardingState> get() = _state

    private var step: Int = 0
    private val selectedConcerns = mutableListOf<HealthConcern>()
    private val selectedDiets = mutableListOf<Diet>()
    private val allergies = mutableListOf<Allergy>()

    private val _nextStep = MutableLiveData<Int>()
    val nextStep: LiveData<Int> get() = _nextStep

    private val _popBack = MutableLiveData<Unit>()
    val popBack: LiveData<Unit> get() = _popBack

    private val _result = MutableLiveData<String>()
    val result: LiveData<String> get() = _result

    init {
        setInitialState()
    }

    private fun setInitialState() {
        _state.postValue(OnboardingState.GetStarted)
    }

    fun onNext(state: OnboardingState) {
        step++
        _nextStep.postValue(step.times(25))
        _state.postValue(state)
    }

    fun onPrevious() {
        step--
        _nextStep.postValue(step.times(25))
        _popBack.postValue(Unit)
    }

    fun getVitaminResult(
        isDailyExposure: Boolean,
        isSmoke: Boolean,
        alcohol: String,
    ) {
        val data = VitaminResult(
            healthConcerns = selectedConcerns.toList(),
            diets = selectedDiets.toList(),
            isDailyExposure = isDailyExposure,
            isSmoke = isSmoke,
            alcohol = alcohol,
            allergies = allergies
        )
        _result.postValue(
            Gson().toJson(data)
        )
    }

    fun saveSelectedConcerns(concerns: List<HealthConcern>) {
        selectedConcerns.addAll(concerns)
    }

    fun saveSelectedDiets(diets: List<Diet>) {
        selectedDiets.addAll(diets)
    }

    fun saveAllergies(inputs: List<Allergy>) {
        allergies.addAll(inputs)
    }
}

sealed class OnboardingState {
    object GetStarted : OnboardingState()
    object HealthConcern : OnboardingState()
    object Diets : OnboardingState()
    object Allergies : OnboardingState()
    object AdditionalInfo : OnboardingState()
}