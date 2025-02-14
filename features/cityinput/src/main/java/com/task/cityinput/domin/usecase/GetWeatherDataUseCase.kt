package com.task.cityinput.domin.usecase

import com.task.data.entities.GetWeatherData
import com.task.data.repository.Repository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class GetWeatherDataUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(
        lat: String,
        lon: String,
    ): GetWeatherData? {
        return repository.getWeatherData(lat = lat, lon = lon)
    }

}