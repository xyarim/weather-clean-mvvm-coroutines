package com.xyarim.weather.weather.current

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.xyarim.domain.models.CurrentForecast
import com.xyarim.domain.models.DayForecast
import com.xyarim.domain.models.Location
import com.xyarim.weather.ui.theme.components.WeatherDivider
import com.xyarim.weather.utils.SingleEventEffect
import com.xyarim.weather.weather.current.filter.FilterOption
import com.xyarim.weather.weather.current.filter.WeatherFilterOptions

@Composable
fun WeatherMainScreen(
    photosListViewModel: MainViewModel = hiltViewModel(),
    openDayForecast: (String) -> Unit,
) {
    Scaffold { padding ->
        val mainScreenState by photosListViewModel.stateUi.collectAsStateWithLifecycle(initialValue = WeatherMainViewState.Loading)

        mainScreenState.let { data ->
            when (data) {
                is WeatherMainViewState.Error -> Text(data.message)
                WeatherMainViewState.Loading -> Text("Loading...")
                is WeatherMainViewState.Success -> WeatherMainScreenContent(
                    location = data.location,
                    currentForecast = data.currentForecast,
                    daysForecast = data.daysForecast,
                    filterOptions = data.filterOptions,
                    selectedOption = data.selectedOption,
                    onSelectDate = photosListViewModel::onDaySelected,
                    onSelectOption = photosListViewModel::onSelectFilter,
                    modifier = Modifier
                        .padding(padding)
                        .padding(16.dp)
                )
            }
        }

        SingleEventEffect(photosListViewModel.sideEffectFlow) { sideEffect ->
            when (sideEffect) {
                is SideEffect.NavigateToDay -> openDayForecast.invoke(sideEffect.date)
                else -> {}
            }
        }

    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WeatherMainScreenContent(
    location: Location,
    currentForecast: CurrentForecast,
    daysForecast: List<DayForecast>,
    filterOptions: List<FilterOption>,
    selectedOption: FilterOption,
    onSelectOption: (FilterOption) -> Unit,
    onSelectDate: (DayForecast) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        item {
            CurrentWeatherItem(currentForecast, location)
        }
        stickyHeader {
            WeatherFilterOptions(
                dateOptions = filterOptions,
                onSelectedChange = onSelectOption,
                selected = selectedOption
            )
        }
        items(daysForecast) { weather ->
            WeatherItem(weather, onSelectDate)
            WeatherDivider()
        }
    }

}
