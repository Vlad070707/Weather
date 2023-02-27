package com.example.weather.data.repository

import com.example.weather.data.api.SearchCityApi
import com.example.weather.data.api.city_models.ListOfHintsDto
import com.example.weather.util.Resource
import javax.inject.Inject

class SearchCityRepository @Inject constructor(
  private val searchCityApi: SearchCityApi
) {
  suspend fun searchCity(query: String): Resource<ListOfHintsDto> {
    val response = try {
      searchCityApi.searchCity(query)
    } catch (e: Exception) {
      return Resource.Error(e.localizedMessage)
    }
    return Resource.Success(response)
  }
}