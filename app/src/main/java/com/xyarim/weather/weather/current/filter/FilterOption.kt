package com.xyarim.weather.weather.current.filter

data class FilterOption(
    val days: Int,
    val label: String
)

object FilterOptions {
    val options = listOf(
        FilterOption(days = 3, label = "For 3 days"),
        FilterOption(days = 7, label = "For 7 days"),
        FilterOption(days = 10, label = "For 10 days")
    )
}