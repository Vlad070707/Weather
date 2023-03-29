package com.example.domain.location.usecase

import com.example.domain.location.UserPreferencesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SaveLocationUseCase(private val repository: UserPreferencesRepository) {

    suspend operator fun invoke(
        city: String
    ) {
        withContext(Dispatchers.IO) {
            repository.saveCity(city)
        }
    }
}