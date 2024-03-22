package com.vodafone.assignment.data.weather.datasource

import com.vodafone.assignment.data.weather.model.WeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherRemoteDataSource {

    @GET("onecall?exclude=$EXCLUDE_ALL_BUT_CURRENT&appid=$API_KEY")
    suspend fun getCurrentWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") units: String = "metric"
    ): WeatherDto

    @GET("onecall?exclude=$EXCLUDE_ALL_BUT_DAILY&appid=$API_KEY")
    suspend fun getDailyForecast(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") units: String = "metric"
    ): WeatherDto

    companion object {
        const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
        // API key must be hidden locally (e.g. in gradle.properties)
        // But I let it here for vodafone for easy testing if they want
        const val API_KEY = "1167af3989bd6ad1819b34046267bd36"
        const val EXCLUDE_ALL_BUT_CURRENT = "minutely,hourly,daily"
        const val EXCLUDE_ALL_BUT_DAILY = "minutely,hourly,current"
    }
}
