package com.vodafone.assignment.currentweather

import com.vodafone.assignment.core.presentation.ErrorState
import com.vodafone.assignment.domain.weather.model.Weather

internal data class CurrentWeatherUiState(
    val isLoading: Boolean = false,
    val weather: Weather = Weather.empty(),
    val errorState: ErrorState = ErrorState()
)
