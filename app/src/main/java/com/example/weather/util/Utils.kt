package com.example.weather.util

import com.example.weather.R
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

  fun getCurrentDate(): String {
    val calendar = Calendar.getInstance()
    val date = calendar.time
    return SimpleDateFormat("EE, dd MMM", Locale.ENGLISH).format(date.time)
  }

}