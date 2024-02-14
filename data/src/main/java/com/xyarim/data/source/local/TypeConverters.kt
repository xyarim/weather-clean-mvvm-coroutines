package com.xyarim.data.source.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


internal class DayForecastListConverter {
    @TypeConverter
    fun fromString(value: String): List<DayForecastEntity> {
        val listType = object : TypeToken<List<DayForecastEntity>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toString(list: List<DayForecastEntity>): String {
        return Gson().toJson(list)
    }
}

internal class HourForecastListConverter {
    @TypeConverter
    fun fromString(value: String): List<HourForecastEntity> {
        val listType = object : TypeToken<List<HourForecastEntity>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toString(list: List<HourForecastEntity>): String {
        return Gson().toJson(list)
    }
}