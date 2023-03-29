package com.example.domain.location.usecase

import com.example.domain.location.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow

class GetSavedLocationUseCase(private val repository: UserPreferencesRepository) {

    operator fun invoke(): Flow<String> = repository.getCity
}