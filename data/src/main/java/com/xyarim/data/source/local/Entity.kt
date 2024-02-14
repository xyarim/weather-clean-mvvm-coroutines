package com.xyarim.data.source.local

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.xyarim.domain.models.CurrentForecast
import com.xyarim.domain.models.DayForecast
import com.xyarim.domain.models.HourForecast
import com.xyarim.domain.models.Location
import com.xyarim.domain.models.WeatherData

@Entity(tableName = "weather_data_table")
internal data class WeatherDataEntity(
    @PrimaryKey()
    val id: Long = 0,
    @Embedded
    val location: LocationEntity,
    @Embedded
    val currentForecast: CurrentForecastEntity,
    @TypeConverters(DayForecastListConverter::class)
    val daysForecast: List<DayForecastEntity>
)

@Entity
internal data class LocationEntity(
    val name: String
)

@Entity
internal data class CurrentForecastEntity(
    val temp: String,
    val code: Int,
    val humidity: String
)

@Entity
internal data class DayForecastEntity(
    val date: String,
    val minTemp: String,
    val maxTemp: String,
    val code: Int,
    @TypeConverters(HourForecastListConverter::class)
    val hours: List<HourForecastEntity>
)

@Entity
data class HourForecastEntity(
    val time: String,
    val temp: String,
    val code: Int
)

private fun HourForecastEntity.asDomainModel() = HourForecast(
    time = this.time,
    code = this.code,
    temp = this.temp
)

private fun HourForecast.asEntity() = HourForecastEntity(
    time = this.time,
    code = this.code,
    temp = this.temp
)

private fun DayForecastEntity.asDomainModel() = DayForecast(
    date = this.date,
    minTemp = this.minTemp,
    maxTemp = this.maxTemp,
    code = this.code,
    hours = this.hours.map { it.asDomainModel() }
)

private fun DayForecast.asEntity() = DayForecastEntity(
    date = this.date,
    minTemp = this.minTemp,
    maxTemp = this.maxTemp,
    code = this.code,
    hours = this.hours.map { it.asEntity() }
)

private fun CurrentForecastEntity.asDomainModel() = CurrentForecast(
    temp = this.temp,
    code = this.code,
    humidity = this.humidity
)

private fun CurrentForecast.asEntity() = CurrentForecastEntity(
    temp = this.temp,
    code = this.code,
    humidity = this.humidity
)

private fun LocationEntity.asDomainModel() = Location(
    name = this.name
)

private fun Location.asEntity() = LocationEntity(
    name = this.name
)

internal fun WeatherDataEntity.asDomainModel() = WeatherData(
    location = this.location.asDomainModel(),
    currentForecast = this.currentForecast.asDomainModel(),
    daysForecast = this.daysForecast.map { it.asDomainModel() }
)

internal fun WeatherData.asEntity() = WeatherDataEntity(
    location = this.location.asEntity(),
    currentForecast = this.currentForecast.asEntity(),
    daysForecast = this.daysForecast.map { it.asEntity() }
)
