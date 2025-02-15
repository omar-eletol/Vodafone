package com.task.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.task.data.entities.CityWeatherEntity

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weather: CityWeatherEntity)

    @Query("SELECT * FROM city_weather LIMIT 1")
    suspend fun getStoredWeather(): CityWeatherEntity?


    @Query("DELETE FROM city_weather")
    suspend fun deleteWeather()

    @Transaction
    suspend fun replaceWeather(weather: CityWeatherEntity) {
        deleteWeather()
        insertWeather(weather)
    }
}
