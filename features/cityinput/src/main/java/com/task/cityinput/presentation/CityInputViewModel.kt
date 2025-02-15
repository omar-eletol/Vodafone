package com.task.cityinput.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.cityinput.domin.usecase.ConvertCityNameUseCase
import com.task.cityinput.domin.usecase.GetStoredWeatherDataUseCase
import com.task.cityinput.domin.usecase.GetWeatherDataUseCase
import com.task.cityinput.domin.usecase.SaveWeatherDataUseCase
import com.task.data.entities.CityWeatherEntity
import com.task.data.entities.Coord
import com.task.data.entities.GetWeatherData
import com.task.data.entities.Main
import com.task.data.entities.Weather
import com.task.data.entities.Wind
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class CityInputViewModel @Inject constructor(
    private val convertCityNameUseCase: ConvertCityNameUseCase,
    private val getWeatherDataUseCase: GetWeatherDataUseCase,
    private val saveWeatherDataUseCase: SaveWeatherDataUseCase,
    private val getStoredWeatherDataUseCase: GetStoredWeatherDataUseCase,

    ) : ViewModel(
) {


    private val _convertCityNameState =
        MutableStateFlow<ConvertCityNameState>(ConvertCityNameState.Idle)
    val convertCityNameState get() = _convertCityNameState.asStateFlow()

    fun convertCityName(cityName: String) = viewModelScope.launch(Dispatchers.IO) {
        _convertCityNameState.emit(ConvertCityNameState.Loading)
        _convertCityNameState.emit(
            try {
                val response = convertCityNameUseCase.invoke(cityName = cityName)
                if (response.isNullOrEmpty()) ConvertCityNameState.Empty
                else {
                    getWeatherData(
                        lat = response.firstOrNull()?.lat.toString(),
                        lon = response.firstOrNull()?.lon.toString()
                    )
                    ConvertCityNameState.Success(data = response)
                }
            } catch (t: Throwable) {
                ConvertCityNameState.Error(throwable = t, errorBody = t.message)
            }
        )

    }

    private val _getWeatherDataState =
        MutableStateFlow<GetWeatherDataState>(GetWeatherDataState.Idle)
    val getWeatherDataState get() = _getWeatherDataState.asStateFlow()

    private fun getWeatherData(lat: String, lon: String) = viewModelScope.launch(Dispatchers.IO) {
        _getWeatherDataState.emit(GetWeatherDataState.Loading)
        _getWeatherDataState.emit(
            try {
                val response = getWeatherDataUseCase.invoke(lat = lat, lon = lon)
                if (response == null) GetWeatherDataState.Empty
                else {
                    saveWeatherData(
                        weather = CityWeatherEntity(
                            cityName = response.name,
                            lon = response.coord.lon,
                            lat = response.coord.lat,
                            feels_like = response.main.feels_like,
                            temp = response.main.temp,
                            speed = response.wind.speed,
                            main = response.weather.firstOrNull()?.main ?: "",
                        )
                    )
                    GetWeatherDataState.Success(data = response)
                }
            } catch (t: Throwable) {
                GetWeatherDataState.Error(throwable = t, errorBody = t.message)
            }
        )

    }


    private fun saveWeatherData(
        weather: CityWeatherEntity,
    ) = viewModelScope.launch(Dispatchers.IO) {
        saveWeatherDataUseCase.invoke(weather = weather)
    }

    fun getStoredWeatherData() = viewModelScope.launch(Dispatchers.IO) {
        _convertCityNameState.emit(ConvertCityNameState.Success(null))
        _getWeatherDataState.emit(GetWeatherDataState.Loading)
        _getWeatherDataState.emit(
            try {
                val response = getStoredWeatherDataUseCase.invoke()
                if (response == null) GetWeatherDataState.Empty
                else {
                    Log.e("getStoredWeatherData", "getStoredWeatherData: ")
                    GetWeatherDataState.Success(
                        data = GetWeatherData(
                            id = response.id,
                            coord = Coord(lat = response.lat, lon = response.lon),
                            main = Main(feels_like = response.feels_like, temp = response.temp),
                            name = response.cityName,
                            weather = listOf(Weather(main = response.main)),
                            wind = Wind(speed = response.speed)
                        )
                    )
                }
            } catch (t: Throwable) {
                Log.e("getStoredWeatherData", "getStoredWeatherData: Throwable")
                GetWeatherDataState.Error(throwable = t, errorBody = t.message)
            }
        )

    }

}