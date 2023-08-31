package com.example.data.usecase

import com.example.domain.search.SearchRepository
import com.example.domain.search.model.HintsList
import com.example.domain.search.usecase.SearchCityUseCase
import javax.inject.Inject

class SearchCityUseCaseImpl @Inject constructor(
    private val repository: SearchRepository
) : SearchCityUseCase {

    override suspend fun invoke(query: String): Result<HintsList> = runCatching {
        repository.searchCity(query = query).getOrThrow()
    }

}