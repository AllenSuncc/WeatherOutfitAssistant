package com.example.myapplication.util.weather

import com.example.myapplication.R

fun getWeatherIcon(description: String): Int {
    return when (description.lowercase()) {
        "clear sky" -> R.drawable.ic_clear
        "few clouds", "scattered clouds" -> R.drawable.ic_partly_cloudy
        "broken clouds", "overcast clouds" -> R.drawable.ic_cloudy
        "light rain", "moderate rain", "heavy rain", "shower rain" -> R.drawable.ic_rain
        "thunderstorm" -> R.drawable.ic_thunderstorm
        "snow" -> R.drawable.ic_snow
        "mist", "fog", "haze" -> R.drawable.ic_mist
        else -> R.drawable.ic_unknown
    }
}
