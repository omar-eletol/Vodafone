package com.task.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city_weather")
data class CityWeatherEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val cityName: String,
    val lon: Double,
    val lat: Double,
    val feels_like: Double,
    val temp: Double,
    val main: String,
    val speed: Double
)