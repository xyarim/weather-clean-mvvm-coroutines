package com.xyarim.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dagger.hilt.android.qualifiers.ApplicationContext

@Database(
    entities = [WeatherDataEntity::class],
    version = AppDatabase.version
)
@TypeConverters(DayForecastListConverter::class, HourForecastListConverter::class)
internal abstract class AppDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDataDao

    companion object {

        private const val name = "weather.db"
        internal const val version = 1

        fun create(
            @ApplicationContext context: Context
        ): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                name
            ).build()
        }
    }
}