package com.task.data.network.service


import com.task.data.entities.ConvertCityNameResponseItem
import com.task.data.entities.GetDaysForeCastResponse
import com.task.data.entities.GetWeatherData
import com.task.data.network.EndPoint.APP_ID
import com.task.data.network.EndPoint.CITY_NAME_CONVERTER
import com.task.data.network.EndPoint.GET_7_DAYS_FORECAST
import com.task.data.network.EndPoint.GET_CITY_WEATHER_DATA
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {


    @GET(CITY_NAME_CONVERTER)
    suspend fun convertCityName(
        @Query("q") cityName: String,
        @Query("appid") appId: String = APP_ID,
        @Query("limit") limit: Int = 1
    ): List<ConvertCityNameResponseItem>

    @GET(GET_CITY_WEATHER_DATA)
    suspend fun getWeatherData(
        @Query("appid") appId: String = APP_ID,
        @Query("lat") lat: String,
        @Query("units") units: String = "metric",
        @Query("lon") lon: String
    ): GetWeatherData

    @GET(GET_7_DAYS_FORECAST)
    suspend fun getDaysForecast(
        @Query("appid") appId: String = APP_ID,
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("units") units: String = "metric",
        @Query("cnt") cnt: Int = 7,
    ): GetDaysForeCastResponse


}