package com.example.data.usecase

import com.example.domain.location.UserPreferencesRepository
import com.example.domain.location.usecase.GetSavedLocationUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSavedLocationUseCaseImpl @Inject constructor(private val repository: UserPreferencesRepository) : GetSavedLocationUseCase {

    override operator fun invoke(): Flow<String> = repository.getCity
}