package com.task.cityinput.domin.entity

import androidx.annotation.Keep

@Keep
data class HistoryUi(
    val id: String?,
    val challengeName: String?,
    val rewardsCount: String?,
    val points: String?,
    val vouchers: String?,
)