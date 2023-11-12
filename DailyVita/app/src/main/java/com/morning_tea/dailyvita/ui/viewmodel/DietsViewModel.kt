package com.morning_tea.dailyvita.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.morning_tea.dailyvita.domain.model.Diet
import com.morning_tea.dailyvita.domain.usecase.GetDietsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DietsViewModel @Inject constructor(
    private val getDietsUseCase: GetDietsUseCase
) : ViewModel() {

    private val _diets = MutableLiveData<List<Diet>>()
    val diets: LiveData<List<Diet>> get() = _diets

    fun getDiets() = viewModelScope.launch(Dispatchers.IO) {
        getDietsUseCase.invoke().collectLatest {
            _diets.postValue(it)
        }
    }

    fun onDietSelected(diet: Diet) {
        val items = diets.value?.map {
            val item = Diet(id = it.id, name = it.name, toolTip = it.toolTip)
            item.isChecked = it.isChecked
            item
        }
        items?.map {
            if (diet.id == 0) {
                it.isChecked = it.id == 0
            }
            if (diet.id != 0) {
                if (it.id == 0) {
                    it.isChecked = false
                } else {
                    if (it.id == diet.id) {
                        it.isChecked = diet.isChecked
                    }
                }
            }
            it
        }?.let {
            _diets.postValue(it)
        }
    }

    fun getSelectedDiets(): List<Diet> = diets.value?.filter { it.id != 0 && it.isChecked } ?: listOf()
}