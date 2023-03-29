package com.example.domain.location.usecase

import com.example.domain.location.UserPreferencesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetSavedLocationUseCase(private val repository: UserPreferencesRepository) {

    suspend operator fun invoke(): String {
        var location = ""
        withContext(Dispatchers.IO) {
            repository.getCity.collect { city ->
                location = city
            }
        }
        return location
    }
}