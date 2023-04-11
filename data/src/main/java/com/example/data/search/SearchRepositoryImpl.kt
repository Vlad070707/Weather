package com.example.data.search

import com.example.domain.search.SearchRepository
import com.example.domain.search.model.ListOfHintsDto
import com.example.domain.util.RequestState

class SearchRepositoryImpl(private val service: SearchCityService) : SearchRepository {

    override suspend fun searchCity(query: String): RequestState<ListOfHintsDto> {
        val response = try {
            service.searchCity(query)
        } catch (e: Exception) {
            return RequestState.Error(e.localizedMessage)
        }
        return RequestState.Success(response)
    }
}