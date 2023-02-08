package com.example.weather.data.api.models

import com.google.gson.annotations.SerializedName

data class Item(
  @SerializedName("clouds")
  val clouds: Clouds? = null,
  @SerializedName("dt")
  val dt: Int? = null,
  @SerializedName("dt_txt")
  val dtTxt: String? = null,
  @SerializedName("main")
  val main: Main? = null,
  @SerializedName("pop")
  val pop: Double? = null,
  @SerializedName("snow")
  val snow: Snow? = null,
  @SerializedName("sys")
  val sys: Sys? = null,
  @SerializedName("visibility")
  val visibility: Int? = null,
  @SerializedName("weather")
  val weather: List<Weather?>? = null,
  @SerializedName("wind")
  val wind: Wind? = null
)