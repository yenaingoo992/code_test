package com.morning_tea.dailyvita.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.morning_tea.dailyvita.domain.model.HealthConcern
import com.morning_tea.dailyvita.domain.usecase.GetHealthConcernsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Collections
import javax.inject.Inject

@HiltViewModel
class HealthConcernsViewModel @Inject constructor(
    private val getHealthConcernsUseCase: GetHealthConcernsUseCase,
): ViewModel() {

    private val _healthConcern = MutableLiveData<List<HealthConcern>>()
    val healthConcern: LiveData<List<HealthConcern>> get() = _healthConcern

    private val concerns = mutableListOf<HealthConcern>()
    private val _selectedConcerns = MutableLiveData<List<HealthConcern>>()
    val selectedConcerns: LiveData<List<HealthConcern>> get() = _selectedConcerns

    fun getHealthConcerns() = viewModelScope.launch(Dispatchers.IO) {
        getHealthConcernsUseCase.invoke().collectLatest {
            _healthConcern.postValue(it)
        }
    }

    fun onHealthConcernClick(healthConcern: HealthConcern) {
        if (concerns.contains(healthConcern)) {
            concerns.remove(healthConcern)
        } else {
            concerns.add(healthConcern)
        }
        if (concerns.size > 5) {
            concerns.remove(healthConcern)
            throw IllegalArgumentException("Only 5 items can be selected")
        }
        _selectedConcerns.postValue(concerns.map { it.copy() })
    }

    fun swipe(from: Int, to: Int) {
        Collections.swap(concerns, from, to)
    }

    fun getSelectedConcerns(): List<HealthConcern> = concerns.toList()
}