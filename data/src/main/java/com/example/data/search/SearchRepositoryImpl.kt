package com.example.data.search

import com.example.domain.search.SearchRepository
import com.example.domain.search.model.ListOfHintsDto
import com.example.domain.util.Resource

class SearchRepositoryImpl(private val service: SearchCityService) : SearchRepository {

    override suspend fun searchCity(query: String): Resource<ListOfHintsDto> {
        val response = try {
            service.searchCity(query)
        } catch (e: Exception) {
            return Resource.Error(e.localizedMessage)
        }
        return Resource.Success(response)
    }
}