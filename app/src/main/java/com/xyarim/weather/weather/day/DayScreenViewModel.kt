package com.xyarim.weather.weather.day

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xyarim.domain.models.DayForecast
import com.xyarim.domain.repository.WeatherRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class DayScreenState(
    val dayForecast: DayForecast? = null
)

@HiltViewModel(assistedFactory = DayScreenViewModel.DayScreenViewModelFactory::class)
class DayScreenViewModel @AssistedInject constructor(
    private val weatherRepository: WeatherRepository,
    @Assisted val date: String?,
) : ViewModel() {

    private val _uiState = MutableStateFlow(DayScreenState())
    val uiState: StateFlow<DayScreenState> = _uiState

    init {
        viewModelScope.launch {
            date?.let { fetchData(it) }
        }
    }

    private suspend fun fetchData(date: String) {
        try {
            val photos = weatherRepository.getWeatherByDay(date)
            viewModelScope.launch {
                _uiState.value = _uiState.value.copy(dayForecast = photos)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @AssistedFactory
    interface DayScreenViewModelFactory {
        fun create(date: String): DayScreenViewModel
    }

}