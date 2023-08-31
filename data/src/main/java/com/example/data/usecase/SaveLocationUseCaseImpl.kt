package com.example.data.usecase

import com.example.domain.location.UserPreferencesRepository
import com.example.domain.location.usecase.SaveLocationUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SaveLocationUseCaseImpl @Inject constructor(private val repository: UserPreferencesRepository) : SaveLocationUseCase {

    override suspend operator fun invoke(
        city: String
    ) = withContext(Dispatchers.IO) {
        repository.saveCity(city)
    }

}