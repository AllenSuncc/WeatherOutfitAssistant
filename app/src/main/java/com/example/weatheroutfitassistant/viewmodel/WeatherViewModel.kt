package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.model.WeatherResponse
import com.example.myapplication.data.remote.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    // Read-only state to expose weather data externally
    private val _weather = MutableStateFlow<WeatherResponse?>(null)
    val weather: StateFlow<WeatherResponse?> = _weather

    // Your OpenWeatherMap API key
    private val apiKey = "007e091705ae5a42604ef90d694ad695"

    // Latitude and longitude of Melbourne
    private val melbourneLat = -37.8136
    private val melbourneLon = 144.9631

    // Call this function to fetch weather data
    fun fetchMelbourneWeather() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getWeatherByCoordinates(
                    lat = melbourneLat,
                    lon = melbourneLon,
                    apiKey = apiKey
                )
                _weather.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
