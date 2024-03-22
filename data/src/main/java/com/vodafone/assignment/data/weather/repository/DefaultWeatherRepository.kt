package com.vodafone.assignment.data.weather.repository

import com.vodafone.assignment.data.weather.datasource.WeatherRemoteDataSource
import com.vodafone.assignment.data.weather.model.WeatherDto
import com.vodafone.assignment.data.weather.util.asCurrentWeather
import com.vodafone.assignment.data.weather.util.asDailyForecast
import com.vodafone.assignment.domain.weather.model.Weather
import com.vodafone.assignment.domain.weather.repository.WeatherRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultWeatherRepository @Inject constructor(
    private val remoteDataSource: WeatherRemoteDataSource,
    private val dispatcher: CoroutineDispatcher
) : WeatherRepository {

    override suspend fun getCurrentWeather(
        latitude: Double,
        longitude: Double
    ): Weather = withContext(dispatcher) {
        val weatherDto: WeatherDto = remoteDataSource.getCurrentWeather(latitude, longitude)
        return@withContext weatherDto.asCurrentWeather()
    }

    override suspend fun getDailyForecast(
        latitude: Double,
        longitude: Double
    ): List<Weather> = withContext(dispatcher) {
        val forecastDto: WeatherDto = remoteDataSource.getDailyForecast(latitude, longitude)
        return@withContext forecastDto.asDailyForecast()
    }
}
