package com.xyarim.weather.utils

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

fun String.convertDateString(): String {
    val formatter = DateTimeFormatter.ISO_LOCAL_DATE
    val date = LocalDate.parse(this, formatter)
    val currentDay = LocalDate.now()
    return when (ChronoUnit.DAYS.between(currentDay, date)) {
        0L -> "TODAY"
        else -> date.dayOfWeek.toString()
    }
}
fun String.convertToHourString(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    val dateTime = LocalDateTime.parse(this, formatter)
    val hourFormatter = DateTimeFormatter.ofPattern("h a")
    return dateTime.format(hourFormatter)
}