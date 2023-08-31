package com.example.domain.location.usecase

import kotlinx.coroutines.flow.Flow

interface GetSavedLocationUseCase {

    operator fun invoke(): Flow<String>
}