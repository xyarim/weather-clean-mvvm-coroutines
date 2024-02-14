package com.xyarim.weather.ui.theme.components

import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WeatherDivider(
    modifier: Modifier = Modifier
) {
    Divider(
        modifier = modifier,
        thickness = 8.dp,
        color = MaterialTheme.colorScheme.surface
    )
}