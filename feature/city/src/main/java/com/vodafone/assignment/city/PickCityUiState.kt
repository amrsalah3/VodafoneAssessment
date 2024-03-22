package com.vodafone.assignment.city

import com.vodafone.assignment.core.presentation.ErrorState
import com.vodafone.assignment.domain.city.model.City

internal data class PickCityUiState(
    val isLoading: Boolean = false,
    val searchQuery: String = "",
    val cities: List<City> = emptyList(),
    val errorState: ErrorState = ErrorState()
)

