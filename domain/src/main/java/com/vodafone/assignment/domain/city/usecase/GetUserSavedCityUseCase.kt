package com.vodafone.assignment.domain.city.usecase

import com.vodafone.assignment.domain.city.model.City
import com.vodafone.assignment.domain.city.repository.CityRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserSavedCityUseCase @Inject constructor(
    private val cityRepository: CityRepository
) {
    operator fun invoke(): Flow<City> = cityRepository.getSavedCity()
}
