package com.vodafone.assignment.core.di

import com.vodafone.assignment.data.weather.datasource.WeatherRemoteDataSource
import com.vodafone.assignment.data.weather.repository.DefaultWeatherRepository
import com.vodafone.assignment.domain.weather.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class WeatherDataModule {

    @Binds
    abstract fun bindWeatherRepository(
        weatherRepository: DefaultWeatherRepository,
    ): WeatherRepository

    companion object {
        @Qualifier
        annotation class WeatherApiClient

        @Provides
        @Singleton
        @WeatherApiClient
        fun provideWeatherApiRetrofitClient(): Retrofit = Retrofit.Builder()
            .baseUrl(WeatherRemoteDataSource.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        @Provides
        @Singleton
        fun provideWeatherRemoteDataSource(@WeatherApiClient retrofit: Retrofit)
                : WeatherRemoteDataSource = retrofit.create(WeatherRemoteDataSource::class.java)
    }
}
