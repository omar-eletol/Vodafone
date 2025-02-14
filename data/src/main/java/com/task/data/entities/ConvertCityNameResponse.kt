package com.task.data.entities

import androidx.annotation.Keep

@Keep
data class ConvertCityNameResponseItem(
    val lat: Double,
    val lon: Double,
    val name: String,
    val state: String,
)