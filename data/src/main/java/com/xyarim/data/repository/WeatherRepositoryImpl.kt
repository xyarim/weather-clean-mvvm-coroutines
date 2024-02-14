package com.xyarim.data.repository

import com.xyarim.data.source.LocalDataSource
import com.xyarim.data.source.RemoteDataSource
import com.xyarim.domain.models.DayForecast
import com.xyarim.domain.models.Result
import com.xyarim.domain.models.WeatherData
import com.xyarim.domain.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class WeatherRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : WeatherRepository {

    override suspend fun getWeatherForecast(): Flow<Result<WeatherData>> =
        flow {
            emit(Result.Loading)
            delay(10000)
            try {
                val localWeatherData =
                    withContext(Dispatchers.IO) { localDataSource.getWeatherForecast() }
                if (localWeatherData != null) {
                    emit(Result.Success(localWeatherData))
                }
                val remoteWeatherData =
                    withContext(Dispatchers.IO) { remoteDataSource.getWeatherForecast() }
                localDataSource.saveWeatherForecast(remoteWeatherData)
                emit(Result.Success(remoteWeatherData))
            } catch (e: Exception) {
                emit(Result.Error(e))
            }
        }

    override suspend fun getWeatherByDay(date: String): DayForecast {
        return withContext(Dispatchers.IO) {
            localDataSource.getWeatherForDay(date) ?: throw Exception("No data")
        }
    }
}