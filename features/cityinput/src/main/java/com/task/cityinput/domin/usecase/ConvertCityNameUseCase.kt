package com.task.cityinput.domin.usecase

import com.task.data.entities.ConvertCityNameResponseItem
import com.task.data.repository.Repository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class ConvertCityNameUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(
        cityName: String,
    ): List<ConvertCityNameResponseItem>? {
        return repository.convertCityName(cityName = cityName)
    }

}

