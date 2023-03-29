package com.example.domain.search.model

import com.google.gson.annotations.SerializedName

data class ListOfHintsDto(
  @SerializedName("geonames")
  val geonames: List<Geoname?>? = null,
  @SerializedName("totalResultsCount")
  val totalResultsCount: Int? = null
)