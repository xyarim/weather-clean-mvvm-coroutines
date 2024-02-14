package com.xyarim.weather.weather.current

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xyarim.domain.models.DayForecast
import com.xyarim.domain.models.Result
import com.xyarim.domain.models.WeatherData
import com.xyarim.domain.repository.WeatherRepository
import com.xyarim.weather.weather.current.filter.FilterOption
import com.xyarim.weather.weather.current.filter.FilterOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface SideEffect {
    data class ShowToast(val message: String) : SideEffect
    data class NavigateToDay(val date: String) : SideEffect
}

@HiltViewModel
class MainViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {
    private val _sideEffectChannel = Channel<SideEffect>(capacity = Channel.BUFFERED)
    val sideEffectFlow: Flow<SideEffect> get() = _sideEffectChannel.receiveAsFlow()

    private val _selectedDaysFilter = MutableStateFlow(FilterOptions.options.first())
    private val _filterOptions = MutableStateFlow(FilterOptions.options)

    private val _data: MutableStateFlow<Result<WeatherData>> = MutableStateFlow(Result.Loading)

    val stateUi: Flow<WeatherMainViewState> = combine(
        _data,
        _selectedDaysFilter,
        _filterOptions
    ) { data,
        selectedDaysFilter,
        filterOptions
        ->
        when (data) {
            Result.Loading -> WeatherMainViewState.Loading
            is Result.Error -> WeatherMainViewState.Error(data.exception.toString())
            is Result.Success -> {
                WeatherMainViewState.Success(
                    location = data.data.location,
                    daysForecast = data.data.daysForecast.take(selectedDaysFilter.days),
                    currentForecast = data.data.currentForecast,
                    filterOptions = filterOptions,
                    selectedOption = selectedDaysFilter
                )
            }
        }
    }

    init {
        viewModelScope.launch {
            weatherRepository.getWeatherForecast().collect { result ->
                _data.value = result
            }
        }
    }

    fun onSelectFilter(filterOption: FilterOption) {
        _selectedDaysFilter.value = filterOption
    }

    fun onDaySelected(dayForecast: DayForecast) {
        viewModelScope.launch {
            _sideEffectChannel.send(SideEffect.NavigateToDay(dayForecast.date))
        }
    }

}