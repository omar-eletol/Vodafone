package com.task.data.entities

import androidx.annotation.Keep

@Keep
data class GetWeatherData(
    val id: Int,
    val main: Main,
    val name: String,
    val weather: List<Weather>,
    val wind: Wind
)

data class Main(
    val feels_like: Double,
    val temp: Double,
    val temp_max: Double,
    val temp_min: Double
)


data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)

data class Wind(
    val deg: Int,
    val speed: Double
)