package com.xyarim.data.source

import com.xyarim.domain.models.DayForecast
import com.xyarim.domain.models.WeatherData

internal interface LocalDataSource {
    suspend fun getWeatherForecast(): WeatherData?
    suspend fun getWeatherForDay(date: String): DayForecast?
    suspend fun saveWeatherForecast(weatherData: WeatherData)
}
