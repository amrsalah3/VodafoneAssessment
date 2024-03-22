package com.vodafone.assignment.city

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vodafone.assignment.core.presentation.ErrorState
import com.vodafone.assignment.domain.city.model.City
import com.vodafone.assignment.domain.city.usecase.GetCitiesUseCase
import com.vodafone.assignment.domain.city.usecase.SaveCityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PickCityViewModel @Inject constructor(
    private val getCitiesUseCase: GetCitiesUseCase,
    private val saveCityUseCase: SaveCityUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PickCityUiState())
    internal val uiState = _uiState.asStateFlow()

    fun searchForCities(name: String) = viewModelScope.launch {
        if (name.isBlank()) {
            _uiState.update { PickCityUiState(searchQuery = name) }
            return@launch
        }

        _uiState.update { PickCityUiState(isLoading = true, searchQuery = name) }
        try {
            val cities = getCitiesUseCase(name)
            _uiState.update { PickCityUiState(searchQuery = name, cities = cities) }
        } catch (e: Exception) {
            val errorState = ErrorState(
                hasError = true,
                message = "Error while searching for $name ${e.message ?: ""}"
            )
            _uiState.update { PickCityUiState(searchQuery = name, errorState = errorState) }
        }
    }

    fun selectCity(city: City) = viewModelScope.launch {
        saveCityUseCase(city)
    }
}

