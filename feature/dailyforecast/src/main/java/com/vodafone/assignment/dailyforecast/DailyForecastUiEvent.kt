package com.vodafone.assignment.dailyforecast

internal sealed class DailyForecastUiEvent {
    object UpdateDailyForecast : DailyForecastUiEvent()
}
