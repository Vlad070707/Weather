package com.example.weather.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserPreferencesRepository @Inject constructor(
  private val dataStore: DataStore<Preferences>
) {

  val getCity = dataStore.data
    .map { preferences ->
      preferences[CITY_KEY] ?: ""
    }

  suspend fun saveCity(city: String) {
    dataStore.edit { preferences ->
      preferences[CITY_KEY] = city
    }
  }

  companion object {
    private val CITY_KEY = stringPreferencesKey("city")
  }
}