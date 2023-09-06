package com.example.presentation.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.location.usecase.GetSavedLocationUseCase
import com.example.domain.location.usecase.SaveLocationUseCase
import com.example.domain.search.model.HintsList
import com.example.domain.search.usecase.SearchCityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val searchCityUseCase: SearchCityUseCase,
    private val getSavedLocationUseCase: GetSavedLocationUseCase,
    private val saveLocationUseCase: SaveLocationUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<LocationUiState> = MutableStateFlow(LocationUiState())
    val uiState: StateFlow<LocationUiState> = _uiState.asStateFlow()

    private var debounceJob: Job? = null

    init {
        viewModelScope.launch {
            getSavedLocationUseCase().collect { city ->
                _uiState.update {
                    LocationUiState(
                        currentCity = city
                    )
                }
            }
        }
    }

    fun searchCity(query: String) {
        if (_uiState.value.isLoading) return
        _uiState.update {
            it.copy(
                query = query
            )
        }
        viewModelScope.launch {
            debounceJob?.cancel()
            debounceJob = CoroutineScope(Dispatchers.IO).launch {
                delay(600)
                _uiState.update {
                    it.copy(
                        isLoading = true
                    )
                }
                searchCityUseCase(query)
                    .fold(
                        onSuccess = { hintsList ->
                            _uiState.update {
                                it.copy(
                                    hintsList = hintsList,
                                    isLoading = false
                                )
                            }
                        },
                        onFailure = { throwable ->
                            Timber.e(throwable)
                            _uiState.update {
                                it.copy(
                                    isLoading = false
                                )
                            }
                        }
                    )
            }

        }
    }

    fun updateSearchBarFocusStatus(isFocused: Boolean) {
        _uiState.update {
            it.copy(
                isSearchBarFocused = isFocused
            )
        }
    }

    fun clearFocusOnSearchBar() {
        _uiState.update {
            it.copy(
                isSearchBarFocused = false,
                hintsList = HintsList(),
                query = ""
            )
        }
    }

    fun updateCurrentCity(city: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true
                )
            }
            saveLocationUseCase(city)
            _uiState.update {
                LocationUiState(
                    currentCity = city
                )
            }
            clearFocusOnSearchBar()
        }
    }

    fun countryCodeToEmojiFlag(countryCode: String): String {
        return countryCode
            .uppercase(Locale.getDefault())
            .map { char ->
                Character.codePointAt("$char", 0) - 0x41 + 0x1F1E6
            }
            .map { codePoint ->
                Character.toChars(codePoint)
            }
            .joinToString(separator = "") { charArray ->
                String(charArray)
            }
    }
}

data class LocationUiState(
    val hintsList: HintsList = HintsList(),
    val query: String = "",
    val errorMessage: String? = null,
    val isLoading: Boolean = false,
    val isSearchBarFocused: Boolean = false,
    val currentCity: String = ""
)