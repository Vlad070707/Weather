package com.example.weather.data.api.city_models

import com.google.gson.annotations.SerializedName

data class SearchCityDto(
  @SerializedName("geonames")
  val geonames: List<Geoname?>? = null,
  @SerializedName("totalResultsCount")
  val totalResultsCount: Int? = null
)