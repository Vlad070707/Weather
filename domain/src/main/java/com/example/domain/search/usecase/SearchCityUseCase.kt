package com.example.domain.search.usecase

import com.example.domain.search.SearchRepository
import com.example.domain.search.model.ListOfHintsDto
import com.example.domain.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchCityUseCase(private val repository: SearchRepository) {

    suspend operator fun invoke(
        query: String
    ): Resource<ListOfHintsDto> {
        var listOfHints: Resource<ListOfHintsDto>
        withContext(Dispatchers.IO) {
            listOfHints = repository.searchCity(query = query)
        }
        return listOfHints
    }
}