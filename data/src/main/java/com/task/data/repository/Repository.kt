package com.task.data.repository

import com.task.data.entities.CityWeatherEntity
import com.task.data.entities.ConvertCityNameResponseItem
import com.task.data.entities.GetDaysForeCastResponse
import com.task.data.entities.GetWeatherData


interface Repository {


    suspend fun convertCityName(
        cityName: String,
    ): List<ConvertCityNameResponseItem>?

    suspend fun getWeatherData(
        lat: String, lon: String
    ): GetWeatherData?

    suspend fun getDaysForecast(
        lat: String, lon: String
    ): GetDaysForeCastResponse?

    suspend fun saveWeather(
        weather: CityWeatherEntity
    )

    suspend fun getStoredWeather(): CityWeatherEntity?


}