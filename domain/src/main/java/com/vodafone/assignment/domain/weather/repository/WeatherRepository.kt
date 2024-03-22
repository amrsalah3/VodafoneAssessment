package com.vodafone.assignment.domain.weather.repository

import com.vodafone.assignment.domain.weather.model.Weather

interface WeatherRepository {
    suspend fun getCurrentWeather(
        latitude: Double,
        longitude: Double
    ): Weather

    suspend fun getDailyForecast(
        latitude: Double,
        longitude: Double
    ): List<Weather>
}
