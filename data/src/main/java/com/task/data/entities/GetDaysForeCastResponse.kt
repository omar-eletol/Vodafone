package com.task.data.entities

import androidx.annotation.Keep

@Keep
data class GetDaysForeCastResponse(
    val list: List<ForeCastListItem>?,
    val message: Int?
)


data class ForeCastListItem(
    val dt_txt: String?,
    val main: MainForeCastListItem?,
    val weather: List<WeatherForeCastListItem>?,
    val wind: WindForeCastListItem?
)

data class MainForeCastListItem(
    val feels_like: Double?,
    val temp: Double?,
    val temp_max: Double?,
    val temp_min: Double?
)


data class WeatherForeCastListItem(
    val description: String?,
    val icon: String?,
    val id: Int?,
    val main: String?
)

data class WindForeCastListItem(
    val deg: Int?,
    val gust: Double?,
    val speed: Double?
)