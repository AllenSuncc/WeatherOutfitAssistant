package com.example.myapplication.data.remote

import com.example.myapplication.data.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    // Use GET method to call the "weather" endpoint of OpenWeatherMap
    @GET("weather")
    suspend fun getWeatherByCoordinates(
        @Query("lat") lat: Double,                 // Latitude parameter
        @Query("lon") lon: Double,                 // Longitude parameter
        @Query("appid") apiKey: String,            // Your API key
        @Query("units") units: String = "metric"   // Temperature unit, default is Celsius
    ): WeatherResponse
}
