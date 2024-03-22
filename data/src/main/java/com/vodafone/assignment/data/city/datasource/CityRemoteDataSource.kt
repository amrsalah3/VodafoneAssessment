package com.vodafone.assignment.data.city.datasource

import com.vodafone.assignment.data.city.model.CityDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CityRemoteDataSource {

    @GET("direct?appid=$API_KEY")
    suspend fun getCities(
        @Query("q") name: String,
        @Query("limit") limit: Int = 20,
    ): List<CityDto>

    companion object {
        const val BASE_URL = "https://api.openweathermap.org/geo/1.0/"

        // API key must be hidden locally (e.g. in gradle.properties)
        // But I let it here for vodafone for easy testing if they want
        const val API_KEY = "6ec691a4252aec06fa5136f4fb4dfd6b"
    }
}
