package com.example.weather.data.api.weather_models

import com.google.gson.annotations.SerializedName

data class FutureWeatherDto(
  @SerializedName("city")
  val city: City? = null,
  @SerializedName("cnt")
  val cnt: Int? = null,
  @SerializedName("cod")
  val cod: String? = null,
  @SerializedName("list")
  val list: List<Item>,
  @SerializedName("message")
  val message: Int? = null
) : WeatherDto