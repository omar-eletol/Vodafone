package com.task.forecastlist.presentation

import com.task.data.entities.GetDaysForeCastResponse


internal sealed class ForeCastListState {
    data class Success(val data: GetDaysForeCastResponse?) : ForeCastListState()
    data class Error(val throwable: Throwable, val errorBody: String?) : ForeCastListState()
    data object Loading : ForeCastListState()
    data object Idle : ForeCastListState()
    data object Empty : ForeCastListState()
}




