package com.task.data.repository

import com.task.data.datasource.database.WeatherDao
import com.task.data.entities.CityWeatherEntity
import com.task.data.entities.ConvertCityNameResponseItem
import com.task.data.entities.GetDaysForeCastResponse
import com.task.data.entities.GetWeatherData
import com.task.data.datasource.network.service.ApiService
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val weatherDao: WeatherDao
) : Repository {


    override suspend fun convertCityName(cityName: String): List<ConvertCityNameResponseItem> {
        return apiService.convertCityName(cityName = cityName)
    }

    override suspend fun getWeatherData(lat: String, lon: String): GetWeatherData {
        return apiService.getWeatherData(lat = lat, lon = lon)
    }

    override suspend fun getDaysForecast(lat: String, lon: String): GetDaysForeCastResponse {
        return apiService.getDaysForecast(lat = lat, lon = lon)
    }

    override suspend fun saveWeather(weather: CityWeatherEntity) {
        return weatherDao.replaceWeather(weather = weather)
    }

    override suspend fun getStoredWeather(): CityWeatherEntity? {
        return weatherDao.getStoredWeather()
    }


}