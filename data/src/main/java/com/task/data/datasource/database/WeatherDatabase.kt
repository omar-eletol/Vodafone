package com.task.data.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.task.data.entities.CityWeatherEntity

@Database(entities = [CityWeatherEntity::class], version = 1, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}
