package com.example.presentation.home.view

import com.example.domain.weather.model.CurrentWeather
import com.example.domain.weather.model.FutureWeather
import com.example.domain.weather.model.Item
import com.example.presentation.base.Utils
import java.text.SimpleDateFormat
import java.util.Locale

data class CurrentWeatherViewState(
    val currentWeather: CurrentWeather = CurrentWeather(),
    val showMoreDetails: Boolean = false
)

data class WeatherForNextDaysViewState(
    val futureWeather: FutureWeather = FutureWeather(),
    val daysOfWeatherViewState: DaysOfWeatherViewState = DaysOfWeatherViewState()
) {

    fun getWeatherList(): List<Item> {
        return when {
            daysOfWeatherViewState.isTodayChecked || daysOfWeatherViewState.isTomorrowChecked -> mapWeather()
            else -> futureWeather.list
        }
    }

    private fun mapWeather(): List<Item> {
        val patternForWeather = "yyyy-MM-dd"
        val patternOfItemWeather = "yyyy-MM-dd HH:mm:ss"
        val currentDate =
            if (daysOfWeatherViewState.isTodayChecked) {
                Utils.getDate(pattern = patternForWeather)
            } else {
                Utils.getDate(forTomorrow = true, pattern = patternForWeather)
            }
        val generalListOfWeather = futureWeather.list
        return generalListOfWeather.filter {
            val itemDate = SimpleDateFormat(patternOfItemWeather, Locale.ENGLISH).parse(it.dtTxt.toString())
            val formattedDate = SimpleDateFormat(patternForWeather, Locale.ENGLISH).format(itemDate?.time ?: "")
            formattedDate == currentDate
        }
    }
}

data class DaysOfWeatherViewState(
    var isTodayChecked: Boolean = true,
    var isTomorrowChecked: Boolean = false,
    var isNextFiveDaysChecked: Boolean = false,
)