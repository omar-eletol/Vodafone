package com.task.data.entities

import androidx.annotation.Keep

@Keep
data class GetWeatherData(
    val id: Int,
    val coord: Coord,
    val main: Main,
    val name: String,
    val weather: List<Weather>,
    val wind: Wind
)

data class Coord(
    val lon: Double,
    val lat: Double,
)

data class Main(
    val feels_like: Double,
    val temp: Double,
)


data class Weather(
    val main: String
)

data class Wind(
    val speed: Double
)