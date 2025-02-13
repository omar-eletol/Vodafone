package com.task.cityinput.presentation

import com.task.cityinput.domin.entity.HistoryUi

internal sealed class ChallengesHistoryState {
    data class Success(val data: List<HistoryUi>) : ChallengesHistoryState()
    data class Error(val throwable: Throwable, val errorBody: String?) : ChallengesHistoryState()
    data object Loading : ChallengesHistoryState()
    data object Idle : ChallengesHistoryState()
    data object Empty : ChallengesHistoryState()
}


