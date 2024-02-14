package com.xyarim.domain.models

data class WeatherData(
    val location: Location,
    val currentForecast: CurrentForecast,
    val daysForecast: List<DayForecast>
)

data class Location(val name: String)

data class CurrentForecast(
    val temp: String,
    val code: Int,
    val humidity: String
)

data class DayForecast(
    val date: String,
    val minTemp: String,
    val maxTemp: String,
    val code: Int,
    val hours: List<HourForecast>
)

data class HourForecast(
    val time: String,
    val temp: String,
    val code: Int,
)


