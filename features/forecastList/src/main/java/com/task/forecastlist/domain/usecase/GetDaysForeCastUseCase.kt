package com.task.forecastlist.domain.usecase

import com.task.data.entities.GetDaysForeCastResponse
import com.task.data.repository.Repository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class GetDaysForeCastUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(
        lat: String,
        lon: String,
    ): GetDaysForeCastResponse? {
        return repository.getDaysForecast(lat = lat, lon = lon)
    }

}

