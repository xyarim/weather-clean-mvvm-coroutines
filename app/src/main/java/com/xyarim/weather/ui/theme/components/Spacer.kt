package com.xyarim.weather.ui.theme.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.xyarim.weather.ui.theme.keyLine2


@Composable
fun WeatherSpacer(
    modifier: Modifier = Modifier,
    size: Dp = keyLine2
) {
    Spacer(modifier = modifier.padding(size))
}
