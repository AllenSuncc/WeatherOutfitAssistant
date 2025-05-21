package com.example.myapplication.data.model

// Top-level data class: the entire API response
data class WeatherResponse(
    val name: String,                 // City name (e.g., "Melbourne")
    val weather: List<Weather>,      // Weather information (usually a list with one item)
    val main: Main,                  // Main parameters like temperature and humidity
    val wind: Wind,                  // Wind speed and direction
    val visibility: Int              // Visibility (in meters)
)

// Weather section: description, icon, etc.
data class Weather(
    val description: String,         // Weather description (e.g., "clear sky")
    val icon: String                 // Icon ID (e.g., "01n")
)

// Main weather data such as temperature
data class Main(
    val temp: Double,                // Current temperature (°C)
    val feels_like: Double,          // Feels-like temperature
    val temp_min: Double,           // Minimum temperature
    val temp_max: Double,           // Maximum temperature
    val pressure: Int,              // Atmospheric pressure
    val humidity: Int               // Humidity percentage
)

// Wind-related fields
data class Wind(
    val speed: Double,              // Wind speed (in m/s)
    val deg: Int                    // Wind direction (in degrees)
)