package com.example.data.search

import com.example.data.search.SearchConstants.END_POINT_SEARCH_CITY
import com.example.data.search.SearchConstants.SEARCH_CITY_USER_NAME
import com.example.domain.search.model.ListOfHintsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchCityService {

    @GET(END_POINT_SEARCH_CITY)
    suspend fun searchCity(
        @Query("name_startsWith") query: String,
        @Query("maxRows") maxRows: Int = 6,
        @Query("username") userName: String = SEARCH_CITY_USER_NAME
    ): ListOfHintsDto
}