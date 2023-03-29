package com.example.domain.search

import com.example.domain.search.model.ListOfHintsDto
import com.example.domain.util.Resource

interface SearchRepository {

    suspend fun searchCity(query: String): Resource<ListOfHintsDto>
}