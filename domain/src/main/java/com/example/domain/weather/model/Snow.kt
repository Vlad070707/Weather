package com.example.domain.weather.model

import com.google.gson.annotations.SerializedName

data class Snow(
    @SerializedName("3h")
    val h: Double? = null
)