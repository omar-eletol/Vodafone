package com.task.cityinput.presentation

import com.task.data.entities.ConvertCityNameResponseItem
import com.task.data.entities.GetWeatherData


sealed class ConvertCityNameState {
    data class Success(val data: List<ConvertCityNameResponseItem>) : ConvertCityNameState()
    data class Error(val throwable: Throwable, val errorBody: String?) : ConvertCityNameState()
    data object Loading : ConvertCityNameState()
    data object Idle : ConvertCityNameState()
    data object Empty : ConvertCityNameState()
}

sealed class GetWeatherDataState {
    data class Success(val data: GetWeatherData) : GetWeatherDataState()
    data class Error(val throwable: Throwable, val errorBody: String?) : GetWeatherDataState()
    data object Loading : GetWeatherDataState()
    data object Idle : GetWeatherDataState()
    data object Empty : GetWeatherDataState()
}



