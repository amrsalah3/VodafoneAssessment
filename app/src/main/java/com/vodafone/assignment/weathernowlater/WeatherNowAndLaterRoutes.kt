package com.vodafone.assignment.weathernowlater

import com.vodafone.assignment.weathernowlater.WeatherNowAndLaterScreens.CURRENT_WEATHER_SCREEN
import com.vodafone.assignment.weathernowlater.WeatherNowAndLaterScreens.DAILY_FORECAST_SCREEN
import com.vodafone.assignment.weathernowlater.WeatherNowAndLaterScreens.PICK_CITY_SCREEN

object WeatherNowAndLaterScreens {
    const val PICK_CITY_SCREEN = "pick_city"
    const val CURRENT_WEATHER_SCREEN = "current_weather"
    const val DAILY_FORECAST_SCREEN = "daily_forecast"
}

object WeatherNowAndLaterDestinations {
    const val PICK_CITY_ROUTE = PICK_CITY_SCREEN
    const val CURRENT_WEATHER_ROUTE = CURRENT_WEATHER_SCREEN
    const val DAILY_FORECAST_ROUTE = DAILY_FORECAST_SCREEN
}
