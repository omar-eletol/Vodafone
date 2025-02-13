package com.task.cityinput.presentation


internal sealed class ChallengesHistoryActions {
    data object FetchPointsToBeExpiredList : ChallengesHistoryActions()
    data object SwipeRefreshCalled : ChallengesHistoryActions()

}