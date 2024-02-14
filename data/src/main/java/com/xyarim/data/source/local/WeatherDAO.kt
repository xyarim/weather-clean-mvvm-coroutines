package com.xyarim.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
internal interface WeatherDataDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherData(weatherDataEntity: WeatherDataEntity)

    @Query("SELECT * FROM weather_data_table LIMIT 1")
    fun getSingleWeatherData(): WeatherDataEntity?

    @Delete
    suspend fun deleteWeatherData(weatherDataEntity: WeatherDataEntity)

    @Query("DELETE FROM weather_data_table")
    suspend fun clear()
}