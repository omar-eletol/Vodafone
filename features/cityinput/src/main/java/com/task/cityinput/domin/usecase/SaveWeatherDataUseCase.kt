package com.task.cityinput.domin.usecase

import com.task.data.entities.CityWeatherEntity
import com.task.data.repository.Repository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class SaveWeatherDataUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(
        weather: CityWeatherEntity,
    ) {
        return repository.saveWeather(weather = weather)
    }

}