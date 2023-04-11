package com.example.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.location.usecase.GetSavedLocationUseCase
import com.example.domain.util.RequestState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel(
    private val getSavedLocationUseCase: GetSavedLocationUseCase
) : ViewModel() {

    private val _currentCityState = MutableStateFlow<RequestState<String>>(RequestState.Loading())
    val currentCityState: StateFlow<RequestState<String>> = _currentCityState

    init {
        updateCurrentCityState()
    }

    fun updateCurrentCityState() {
        viewModelScope.launch {
            _currentCityState.value = RequestState.Loading()
            getSavedLocationUseCase().collect { city ->
                _currentCityState.value = RequestState.Success(city)
            }
        }
    }
}