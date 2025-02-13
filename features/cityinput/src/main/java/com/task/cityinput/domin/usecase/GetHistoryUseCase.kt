package com.task.cityinput.domin.usecase

import com.task.cityinput.domin.entity.HistoryUi
import com.task.data.entities.HistoryResponse
import com.task.data.repository.HistoryRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class GetHistoryUseCase @Inject constructor(
    private val historyRepository: HistoryRepository
) {
    suspend operator fun invoke(
    ): List<HistoryUi> {
        return convertToUiObject(historyResponse = historyRepository.getHistory())
    }


    private fun convertToUiObject(historyResponse: List<HistoryResponse>): List<HistoryUi> {
        return historyResponse.map { response ->
            HistoryUi(
                id = response.id,
                challengeName = response.challengeName,
                rewardsCount = response.rewardsCount,
                points = response.points,
                vouchers = response.vouchers
            )
        }
    }


}