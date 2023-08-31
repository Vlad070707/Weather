package com.example.presentation.splash_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.location.usecase.GetSavedLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val getSavedLocationUseCase: GetSavedLocationUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<SplashUiState> = MutableStateFlow(SplashUiState(isLoading = true))
    val uiState: StateFlow<SplashUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            delay(2000)
            getSavedLocationUseCase().collect { city ->
                _uiState.update {
                    SplashUiState(
                        currentCity = city
                    )
                }
            }
        }
    }

}

data class SplashUiState(
    val isLoading: Boolean = false,
    val currentCity: String = ""
)