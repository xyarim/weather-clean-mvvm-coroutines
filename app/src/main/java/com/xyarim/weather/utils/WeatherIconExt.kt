package com.xyarim.weather.utils

import com.xyarim.weather.R

fun Int.asWeatherIconId() = when (this) {
    1000 -> R.drawable.clear
    1003 -> R.drawable.partly_cloudy
    1006 -> R.drawable.mostly_cloudy
    1009 -> R.drawable.overcast
    1030, -> R.drawable.haze
    1135 -> R.drawable.fog
    1180,1183 -> R.drawable.light_rain
    1186,1189,1192 -> R.drawable.rain
    1195 -> R.drawable.heavy_rain
    1273,1276 -> R.drawable.thunderstorms
    1279,1282 -> R.drawable.thunderstorms1
    else -> R.drawable.na
}

fun Int.asWeatherAnimId() = when (this) {
    1000 -> R.raw.anim_clear
    1003 -> R.raw.anim_partly_cloudy
    1006 -> R.raw.anim_mostly_cloudy
    1009 -> R.raw.anim_overcast
    1030 -> R.raw.anim_haze_smoke
    1135 -> R.raw.anim_fog
    1180,1183,1186,1189,1192 -> R.raw.anim_rainy
    1140 -> R.raw.anim_isolated_shower
    1273,1276,1279,1282 -> R.raw.anim_thunderstorms
    else -> R.raw.anim_clear
}
