package com.task.forecastlist.presentation


internal sealed class ForeCastActions {
    data object FetchForeCastList : ForeCastActions()
}