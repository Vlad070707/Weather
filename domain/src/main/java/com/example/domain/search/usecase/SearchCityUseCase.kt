package com.example.domain.search.usecase

import com.example.domain.search.model.HintsList

interface SearchCityUseCase {

    suspend operator fun invoke(query: String): Result<HintsList>
}