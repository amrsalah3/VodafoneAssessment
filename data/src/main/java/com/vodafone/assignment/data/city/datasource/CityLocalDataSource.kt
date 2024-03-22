package com.vodafone.assignment.data.city.datasource

import com.vodafone.assignment.domain.city.model.City
import kotlinx.coroutines.flow.Flow

interface CityLocalDataSource {
    suspend fun saveCity(city: City)
    fun getSavedCity(): Flow<City>
}
