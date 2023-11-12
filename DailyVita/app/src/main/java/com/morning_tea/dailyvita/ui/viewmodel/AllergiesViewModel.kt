package com.morning_tea.dailyvita.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.morning_tea.dailyvita.domain.model.Allergy
import com.morning_tea.dailyvita.domain.usecase.GetAllergiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllergiesViewModel @Inject constructor(
    private val getAllergiesUseCase: GetAllergiesUseCase
) : ViewModel(){

    private val _allergies = MutableLiveData<List<Allergy>>()
    val allergies: LiveData<List<Allergy>> get() = _allergies

    fun getAllergies() = viewModelScope.launch {
        getAllergiesUseCase.invoke().collectLatest {
            _allergies.postValue(it)
        }
    }
}