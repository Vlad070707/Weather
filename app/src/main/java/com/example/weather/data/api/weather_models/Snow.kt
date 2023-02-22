package com.example.weather.data.api.weather_models

import com.google.gson.annotations.SerializedName

data class Snow(
    @SerializedName("3h")
    val h: Double? = null
)