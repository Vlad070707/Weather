package com.example.weather.util

import com.example.weather.R
import com.example.weather.data.api.model.FutureWeatherDto
import com.example.weather.data.api.model.Item
import java.text.SimpleDateFormat
import java.util.*

object Utils {

  fun getImage(icon: String?): Int {
    return when (icon) {
      "01d" -> R.drawable.d01
      "01n" -> R.drawable.n01
      "02d" -> R.drawable.d02
      "02n" -> R.drawable.n02
      "03d", "03n", "04d", "04n" -> R.drawable.d03
      "09d", "09n" -> R.drawable.d09
      "10d" -> R.drawable.d10
      "10n" -> R.drawable.n10
      "11d", "11n" -> R.drawable.d11
      "13d", "13n" -> R.drawable.d13
      "50d", "50n" -> R.drawable.d50
      else -> R.drawable.d01
    }
  }

  fun getDateFor(forTomorrow: Boolean = false, pattern: String): String {
    val calendar = Calendar.getInstance()
    if (forTomorrow) {
      calendar.add(Calendar.DATE, 1)
    }
    val date = calendar.time
    return SimpleDateFormat(pattern, Locale.ENGLISH).format(date.time)
  }

  fun mapWeather(futureWeatherDto: FutureWeatherDto, isTodayWeather: Boolean = true): List<Item> {
    val patternForWeather = "yyyy-MM-dd"
    val patternOfItemWeather = "yyyy-MM-dd HH:mm:ss"
    val currentDate =
      if (isTodayWeather) {
        getDateFor(pattern = patternForWeather)
      } else {
        getDateFor(forTomorrow = true, pattern = patternForWeather)
      }
    val generalListOfWeather = futureWeatherDto.list
    return generalListOfWeather.filter {
      val itemDate = SimpleDateFormat(patternOfItemWeather, Locale.ENGLISH).parse(it.dtTxt.toString())
      val formattedDate = SimpleDateFormat(patternForWeather, Locale.ENGLISH).format(itemDate?.time ?: "")
      formattedDate == currentDate
    }
  }
}