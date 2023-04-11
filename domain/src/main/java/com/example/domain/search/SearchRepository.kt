package com.example.domain.search

import com.example.domain.search.model.ListOfHintsDto
import com.example.domain.util.RequestState

interface SearchRepository {

    suspend fun searchCity(query: String): RequestState<ListOfHintsDto>
}