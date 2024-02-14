package com.xyarim.weather.weather.current

import com.xyarim.domain.models.CurrentForecast
import com.xyarim.domain.models.DayForecast
import com.xyarim.domain.models.Location
import com.xyarim.weather.weather.current.filter.FilterOption


sealed class WeatherMainViewState {
    data object Loading : WeatherMainViewState()
    data class Error(val message: String) : WeatherMainViewState()
    data class Success(
        val location: Location,
        val currentForecast: CurrentForecast,
        val daysForecast: List<DayForecast>,
        val filterOptions: List<FilterOption>,
        val selectedOption: FilterOption
    ) : WeatherMainViewState()
}