package com.vodafone.assignment.data.city.repository

import com.vodafone.assignment.data.city.datasource.CityLocalDataSource
import com.vodafone.assignment.data.city.datasource.CityRemoteDataSource
import com.vodafone.assignment.data.city.model.CityDto
import com.vodafone.assignment.data.city.util.asCityList
import com.vodafone.assignment.domain.city.model.City
import com.vodafone.assignment.domain.city.repository.CityRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultCityRepository @Inject constructor(
    private val localDataSource: CityLocalDataSource,
    private val remoteDataSource: CityRemoteDataSource,
    private val dispatcher: CoroutineDispatcher
) : CityRepository {

    override suspend fun getCities(name: String): List<City> = withContext(dispatcher) {
        val cityDtos: List<CityDto> = remoteDataSource.getCities(name)
        return@withContext cityDtos.asCityList()
    }

    override suspend fun saveCity(city: City) = withContext(dispatcher) {
        localDataSource.saveCity(city)
    }

    override fun getSavedCity(): Flow<City> = localDataSource.getSavedCity()
}
