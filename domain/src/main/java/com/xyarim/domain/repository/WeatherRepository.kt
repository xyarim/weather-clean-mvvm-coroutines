package com.xyarim.domain.repository

import com.xyarim.domain.models.DayForecast
import com.xyarim.domain.models.WeatherData
import com.xyarim.domain.models.Result
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getWeatherForecast(): Flow<Result<WeatherData>>
    suspend fun getWeatherByDay(date: String): DayForecast
}