package com.example.data.repository

import com.example.data.service.SearchCityService
import com.example.domain.search.SearchRepository
import com.example.domain.search.model.HintsList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchRepositoryImpl(private val service: SearchCityService) : SearchRepository {

    override suspend fun searchCity(query: String): Result<HintsList> = withContext(Dispatchers.IO) {
        try {
            val response = service.searchCity(query)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}