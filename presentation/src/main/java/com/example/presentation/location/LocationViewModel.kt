package com.example.presentation.location

import androidx.lifecycle.viewModelScope
import com.example.domain.location.usecase.GetSavedLocationUseCase
import com.example.domain.location.usecase.SaveLocationUseCase
import com.example.domain.search.model.ListOfHintsDto
import com.example.domain.search.usecase.SearchCityUseCase
import com.example.presentation.base.BaseViewModel
import com.example.domain.util.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class LocationViewModel @Inject constructor(
    private val searchCityUseCase: SearchCityUseCase,
    getSavedLocationUseCase: GetSavedLocationUseCase,
    private val saveLocationUseCase: SaveLocationUseCase
) : BaseViewModel(getSavedLocationUseCase) {

    private val _listOfHintsDtoState = MutableStateFlow<RequestState<ListOfHintsDto>>(RequestState.Error())
    val listOfHintsDtoState: StateFlow<RequestState<ListOfHintsDto>> = _listOfHintsDtoState

    private val _query = MutableSharedFlow<String>()

    init {
        viewModelScope.launch {
            _query
                .debounce(500)
                .collectLatest {
                    _listOfHintsDtoState.value = RequestState.Loading()
                    _listOfHintsDtoState.value = searchCityUseCase(it)
                }
        }
    }

    fun searchCity(query: String) {
        viewModelScope.launch {
            _query.emit(query)
        }
    }

    fun saveCurrentCity(city: String) {
        viewModelScope.launch {
            saveLocationUseCase(city)
        }
    }

    fun clearListOfHints() {
        _listOfHintsDtoState.value = RequestState.Success(ListOfHintsDto())
    }
}