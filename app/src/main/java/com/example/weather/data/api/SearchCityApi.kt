package com.example.weather.data.api

import retrofit2.http.GET
import com.example.weather.data.api.ApiConstants.END_POINT_SEARCH_CITY
import com.example.weather.data.api.ApiConstants.SEARCH_CITY_USER_NAME
import com.example.weather.data.api.city_models.ListOfHintsDto
import retrofit2.http.Query

interface SearchCityApi {

  @GET(END_POINT_SEARCH_CITY)
  suspend fun searchCity(
    @Query("name_startsWith") query: String,
    @Query("maxRows") maxRows: Int = 10,
    @Query("username") userName: String = SEARCH_CITY_USER_NAME
  ): ListOfHintsDto
}