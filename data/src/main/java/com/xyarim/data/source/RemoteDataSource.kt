package com.xyarim.data.source

import com.xyarim.domain.models.WeatherData

internal interface RemoteDataSource {
    suspend fun getWeatherForecast(): WeatherData
}

