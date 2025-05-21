package com.example.myapplication.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    // Base API URL for OpenWeatherMap
    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    // Retrofit singleton, initialized lazily (only created once)
    val api: WeatherApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)                               // Set the base API URL
            .addConverterFactory(GsonConverterFactory.create()) // Use Gson to parse JSON
            .build()
            .create(WeatherApi::class.java)                  // Create the concrete implementation of the interface
    }
}
