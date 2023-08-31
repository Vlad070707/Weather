package com.example.domain.location.usecase

interface SaveLocationUseCase {

    suspend operator fun invoke(city: String)
}