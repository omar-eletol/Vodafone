package com.task.displayingcustomweathericons


fun weatherIcon(weatherCondition: String): Int {
    val weatherDrawable = when (weatherCondition.lowercase()) {
        "clear" -> R.drawable.ic_sunny
        "clouds" -> R.drawable.ic_clouds
        "rain" -> R.drawable.ic_rain
        "snow" -> R.drawable.ic_snow
        else -> R.drawable.ic_empty_response_lib // Default icon
    }
    return weatherDrawable
}
