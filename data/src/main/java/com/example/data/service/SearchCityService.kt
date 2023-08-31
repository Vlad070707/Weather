package com.example.data.service

import com.example.domain.search.model.HintsList
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchCityService {

    @GET(END_POINT_SEARCH_CITY)
    suspend fun searchCity(
        @Query("name_startsWith") query: String,
        @Query("maxRows") maxRows: Int = 6,
        @Query("username") userName: String = SEARCH_CITY_USER_NAME
    ): HintsList

    companion object{
        private const val END_POINT_SEARCH_CITY = "searchJSON?"
        private const val SEARCH_CITY_USER_NAME = "vladyslav.deda"
    }
}