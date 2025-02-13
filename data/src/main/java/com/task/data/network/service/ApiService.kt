package com.task.data.network.service


import com.task.data.entities.HistoryResponse
import com.task.data.network.EndPoint.HISTORY_API_END_POINT
import retrofit2.http.GET


interface ApiService {

    @GET(value = HISTORY_API_END_POINT)
    suspend fun getHistory(): List<HistoryResponse>


}