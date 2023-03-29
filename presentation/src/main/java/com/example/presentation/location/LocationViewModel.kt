package com.example.presentation.location

import androidx.lifecycle.viewModelScope
import com.example.domain.location.usecase.GetSavedLocationUseCase
import com.example.domain.location.usecase.SaveLocationUseCase
import com.example.domain.search.model.ListOfHintsDto
import com.example.domain.search.usecase.SearchCityUseCase
import com.example.presentation.base.BaseViewModel
import com.example.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val searchCityUseCase: SearchCityUseCase,
    getSavedLocationUseCase: GetSavedLocationUseCase,
    private val saveLocationUseCase: SaveLocationUseCase
) : BaseViewModel(getSavedLocationUseCase) {

    private val _listOfHintsDtoState = MutableStateFlow<Resource<ListOfHintsDto>>(Resource.Error())
    val listOfHintsDtoState: StateFlow<Resource<ListOfHintsDto>> = _listOfHintsDtoState

    fun searchCity(query: String) {
        viewModelScope.launch {
            _listOfHintsDtoState.value = Resource.Loading()
            _listOfHintsDtoState.value = searchCityUseCase(query)
        }
    }

    fun saveCurrentCity(city: String) {
        viewModelScope.launch {
            saveLocationUseCase(city)
        }
    }

    fun clearListOfHints() {
        _listOfHintsDtoState.value = Resource.Success(ListOfHintsDto())
    }
}