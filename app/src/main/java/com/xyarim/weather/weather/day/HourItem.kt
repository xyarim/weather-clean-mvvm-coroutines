package com.xyarim.weather.weather.day

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.xyarim.domain.models.HourForecast
import com.xyarim.weather.R
import com.xyarim.weather.ui.theme.components.WeatherSpacer
import com.xyarim.weather.ui.theme.keyLine0
import com.xyarim.weather.ui.theme.keyLine3
import com.xyarim.weather.utils.asWeatherIconId
import com.xyarim.weather.utils.convertToHourString

@Composable
fun HoursForecast(hours: List<HourForecast>){
    LazyRow {
        items(hours) { hour ->
            HourItem(hour)
        }
    }
}
@Composable
fun HourItem(
    hour: HourForecast,
) {
    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(keyLine3),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Text(
                text = hour.time.convertToHourString(),
                style = MaterialTheme.typography.titleMedium
            )
            Image(
                modifier = Modifier.size(40.dp),
                painter = painterResource(id = hour.code.asWeatherIconId()),
                contentDescription = hour.code.toString()
            )
            WeatherSpacer(size = keyLine0)
            Text(
                text = stringResource(id = R.string.temp, hour.temp),
                style = MaterialTheme.typography.titleMedium,
            )
        }

    }
}