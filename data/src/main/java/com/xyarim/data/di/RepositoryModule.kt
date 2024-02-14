package com.xyarim.data.di

import com.xyarim.data.repository.WeatherRepositoryImpl
import com.xyarim.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {
    @Binds
    fun provideWeatherRepository(repositoryImpl: WeatherRepositoryImpl): WeatherRepository
}