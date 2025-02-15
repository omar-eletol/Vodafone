package com.task.cityinput.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.cityinput.domin.usecase.ConvertCityNameUseCase
import com.task.cityinput.domin.usecase.GetWeatherDataUseCase
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

    fun getWeatherData(lat: String, lon: String) = viewModelScope.launch(Dispatchers.IO) {
        _getWeatherDataState.emit(GetWeatherDataState.Loading)
        _getWeatherDataState.emit(
            try {
                val response = getWeatherDataUseCase.invoke(lat = lat, lon = lon)
                if (response == null) GetWeatherDataState.Empty
                else GetWeatherDataState.Success(data = response)
            } catch (t: Throwable) {
                GetWeatherDataState.Error(throwable = t, errorBody = t.message)
            }
        )

    }


}