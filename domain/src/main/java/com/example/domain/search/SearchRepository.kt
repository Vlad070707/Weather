package com.example.domain.search

import com.example.domain.search.model.HintsList

interface SearchRepository {

    suspend fun searchCity(query: String): Result<HintsList>
}