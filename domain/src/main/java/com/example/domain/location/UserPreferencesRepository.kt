package com.example.domain.location

import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepository {

    val getCity: Flow<String>

    suspend fun saveCity(city: String)
}