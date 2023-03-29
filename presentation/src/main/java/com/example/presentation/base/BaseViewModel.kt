package com.example.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.location.usecase.GetSavedLocationUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel(
    private val getSavedLocationUseCase: GetSavedLocationUseCase
) : ViewModel() {

    private val _currentCityState = MutableStateFlow("")
    val currentCityState: StateFlow<String> = _currentCityState

    init {
        updateCurrentCityState()
    }

    fun updateCurrentCityState() {
        viewModelScope.launch {
            getSavedLocationUseCase().collect { city ->
                _currentCityState.value = city
            }
        }
    }
}