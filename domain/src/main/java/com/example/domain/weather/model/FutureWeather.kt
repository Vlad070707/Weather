package com.example.domain.weather.model

import com.google.gson.annotations.SerializedName

data class FutureWeather(
    @SerializedName("city")
    val city: City? = null,
    @SerializedName("cnt")
    val cnt: Int? = null,
    @SerializedName("cod")
    val cod: String? = null,
    @SerializedName("list")
    val list: List<Item> = emptyList(),
    @SerializedName("message")
    val message: Int? = null
)