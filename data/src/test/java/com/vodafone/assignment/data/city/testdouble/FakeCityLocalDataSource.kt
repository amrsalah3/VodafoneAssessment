package com.vodafone.assignment.data.city.testdouble

import com.vodafone.assignment.data.city.datasource.CityLocalDataSource
import com.vodafone.assignment.domain.city.model.City
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

internal class FakeCityLocalDataSource : CityLocalDataSource {

    private var savedCity: City? = null

    override suspend fun saveCity(city: City) {
        savedCity = city
    }

    override fun getSavedCity(): Flow<City> = flowOf(savedCity!!)
}
