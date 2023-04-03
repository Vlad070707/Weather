package com.example.domain.search.model

import com.google.gson.annotations.SerializedName

data class Geoname(
    @SerializedName("adminCode1")
    val adminCode1: String? = null,
    @SerializedName("adminCodes1")
    val adminCodes1: AdminCodes1? = null,
    @SerializedName("adminName1")
    val adminName1: String? = null,
    @SerializedName("countryCode")
    val countryCode: String? = null,
    @SerializedName("countryId")
    val countryId: String? = null,
    @SerializedName("countryName")
    val countryName: String? = null,
    @SerializedName("fcl")
    val fcl: String? = null,
    @SerializedName("fclName")
    val fclName: String? = null,
    @SerializedName("fcode")
    val fcode: String? = null,
    @SerializedName("fcodeName")
    val fcodeName: String? = null,
    @SerializedName("geonameId")
    val geonameId: Int? = null,
    @SerializedName("lat")
    val lat: String? = null,
    @SerializedName("lng")
    val lng: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("population")
    val population: Int? = null,
    @SerializedName("toponymName")
    val toponymName: String? = null
)