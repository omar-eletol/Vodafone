package com.task.data.entities

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class HistoryResponse(
    @SerializedName("\$id")
    val id: String?,
    @SerializedName("challengeName")
    val challengeName: String?,
    @SerializedName("rewardsCount")
    val rewardsCount: String?,
    @SerializedName("points")
    val points: String?,
    @SerializedName("vouchers")
    val vouchers: String?,
    @SerializedName("dummy1")
    val dummy1: String?,
    @SerializedName("dummy2")
    val dummy2: String?,
    @SerializedName("dummy3")
    val dummy3: String?,
    )


