package com.vodafone.assignment.data.weather.util

import com.vodafone.assignment.data.weather.model.WeatherDto
import com.vodafone.assignment.domain.weather.model.Weather

// Map current weather DTO from API model into weather domain model
internal fun WeatherDto.asCurrentWeather(): Weather = current!!.run {
    Weather(
        day = DayFormatter.getDayName(afterNumberOfDays = 0),
        temperature = temp!!,
        humidity = humidity!!.toDouble(),
        windSpeed = windSpeed!!,
        condition = weatherInfo!![0].main!!,
        description = weatherInfo[0].description!!,
        iconUrl = "https://openweathermap.org/img/wn/${weatherInfo[0].icon!!}@2x.png"
    )
}

// Map the list of daily weather forecast DTO from API model into a list of weather domain model
internal fun WeatherDto.asDailyForecast(): List<Weather> {
    val dailyForecast = mutableListOf<Weather>()

    // Drop the first element which is the current day
    daily!!.drop(1).forEachIndexed { index, forecast ->
        dailyForecast += forecast.run {
            Weather(
                day = DayFormatter.getDayName(afterNumberOfDays = index + 1),
                temperature = temp!!.day!!,
                humidity = humidity!!.toDouble(),
                windSpeed = windSpeed!!,
                condition = weatherInfo!![0].main!!,
                description = weatherInfo[0].description!!,
                iconUrl = "https://openweathermap.org/img/wn/${weatherInfo[0].icon!!}@2x.png"
            )
        }
    }

    return dailyForecast
}
