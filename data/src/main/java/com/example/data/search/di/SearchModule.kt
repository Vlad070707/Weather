package com.example.data.search.di

import com.example.data.search.SearchCityService
import com.example.data.search.SearchRepositoryImpl
import com.example.domain.search.SearchRepository
import com.example.domain.search.usecase.SearchCityUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object SearchModule {

    @Provides
    fun provideSearchRepository(searchCityService: SearchCityService): SearchRepository =
        SearchRepositoryImpl(searchCityService)

    @Provides
    fun provideSearchCityUseCase(
        repository: SearchRepository
    ) = SearchCityUseCase(repository)
}