package com.task.data.repository

import com.task.data.entities.HistoryResponse


interface HistoryRepository {

    suspend fun getHistory(
    ): List<HistoryResponse>
}