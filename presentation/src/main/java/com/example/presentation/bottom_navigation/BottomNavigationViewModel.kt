package com.example.presentation.bottom_navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.location.usecase.GetSavedLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BottomNavigationViewModel @Inject constructor(
    getSavedLocationUseCase: GetSavedLocationUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<BottomNavigationUiState> = MutableStateFlow(BottomNavigationUiState())
    val uiState: StateFlow<BottomNavigationUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getSavedLocationUseCase().collect { city ->
                _uiState.update {
                    it.copy(
                        currentCity = city
                    )
                }
            }
        }
    }
}

data class BottomNavigationUiState(
    val currentCity: String = ""
)