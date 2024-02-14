package com.xyarim.data.source.remote

import com.xyarim.data.source.RemoteDataSource
import com.xyarim.domain.models.WeatherData
import javax.inject.Inject

internal class RemoteDataSourceImpl @Inject constructor(private val service: RemoteApiService) :
    RemoteDataSource {
    override suspend fun getWeatherForecast(): WeatherData {
        return service.getForecast(DAYS).mapToDomain()
    }

    private companion object {
        const val DAYS = 10
    }
}