package com.example.weather.data.api.models

import com.google.gson.annotations.SerializedName

data class Clouds(
  @SerializedName("all")
  val all: Int?
)