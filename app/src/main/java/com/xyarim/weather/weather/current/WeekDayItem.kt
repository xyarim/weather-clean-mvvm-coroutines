package com.xyarim.weather.weather.current

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.xyarim.domain.models.DayForecast
import com.xyarim.weather.ui.theme.components.WeatherSpacer
import com.xyarim.weather.ui.theme.keyLine0
import com.xyarim.weather.ui.theme.keyLine3
import com.xyarim.weather.utils.asWeatherIconId
import com.xyarim.weather.utils.convertDateString

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherItem(
    weather: DayForecast,
    onSelectedDateChange: (DayForecast) -> Unit
) {
    Card(modifier = Modifier.padding(4.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary
        ),
        onClick = { onSelectedDateChange.invoke(weather) }) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(keyLine3)
        ) {

            val centerVertically = Modifier.align(Alignment.CenterVertically)

            Image(
                modifier = centerVertically.size(40.dp),
                painter = painterResource(id = weather.code.asWeatherIconId()),
                contentDescription = weather.code.toString()
            )
            WeatherSpacer()
            Column(
                modifier = centerVertically
            ) {
                Text(
                    text = weather.date.convertDateString(),
                    style = MaterialTheme.typography.titleMedium
                )

                WeatherSpacer(size = keyLine0)
                Text(
                    text = "${weather.minTemp} С --> ${weather.maxTemp} С",
                    style = MaterialTheme.typography.titleMedium,
                )
            }
        }
    }
}

