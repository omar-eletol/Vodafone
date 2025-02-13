package com.task.data.repository

import com.task.data.entities.HistoryResponse
import com.task.data.network.service.ApiService
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : HistoryRepository {

    override suspend fun getHistory(
    ): List<HistoryResponse> {
        return apiService.getHistory()
    }
}