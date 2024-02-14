package com.xyarim.data.source.local

import com.xyarim.data.source.LocalDataSource
import com.xyarim.domain.models.DayForecast
import com.xyarim.domain.models.WeatherData
import javax.inject.Inject


internal class LocalDataSourceImpl @Inject constructor(private val dao: WeatherDataDao) :
    LocalDataSource {

    override suspend fun getWeatherForecast(): WeatherData? {
        return dao.getSingleWeatherData()?.asDomainModel()
    }


    override suspend fun getWeatherForDay(date: String): DayForecast? {
        return dao.getSingleWeatherData()
            ?.asDomainModel()?.daysForecast?.firstOrNull { it.date == date }
    }

    override suspend fun saveWeatherForecast(weatherData: WeatherData) {
        dao.insertWeatherData(weatherData.asEntity())
    }
}