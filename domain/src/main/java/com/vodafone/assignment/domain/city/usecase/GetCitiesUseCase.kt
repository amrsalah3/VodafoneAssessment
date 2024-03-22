package com.vodafone.assignment.domain.city.usecase

import com.vodafone.assignment.domain.city.model.City
import com.vodafone.assignment.domain.city.repository.CityRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCitiesUseCase @Inject constructor(
    private val cityRepository: CityRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend operator fun invoke(name: String): List<City> = withContext(dispatcher) {
        return@withContext cityRepository.getCities(name)
    }
}
