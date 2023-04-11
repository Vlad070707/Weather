package com.example.domain.search.usecase

import com.example.domain.search.SearchRepository
import com.example.domain.search.model.ListOfHintsDto
import com.example.domain.util.RequestState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchCityUseCase(private val repository: SearchRepository) {

    suspend operator fun invoke(
        query: String
    ): RequestState<ListOfHintsDto> {
        var listOfHints: RequestState<ListOfHintsDto>
        withContext(Dispatchers.IO) {
            listOfHints = repository.searchCity(query = query)
        }
        return listOfHints
    }
}