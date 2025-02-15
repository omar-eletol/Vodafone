package com.task.core.mainComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.task.core.R


@Composable
fun WeatherCard(
    temperature: String,
    mainWeather: String,
    feelsLike: String,
    windSpeed: String,
    cityName: String,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Image(
                        painterResource(weatherIcon(weatherCondition = mainWeather)),
                        contentDescription = "",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.size(50.dp)
                    )
                    Text(
                        text = buildAnnotatedString {
                            append(temperature)
                            withStyle(
                                SpanStyle(
                                    fontSize = 14.sp, baselineShift = BaselineShift.Superscript
                                )
                            ) {
                                append("°C")
                            }
                        }, style = TextStyle(fontSize = 24.sp)
                    )
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        mainWeather,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.End,
                        style = TextStyle(
                            fontSize = 14.sp, color = Color.Gray
                        )
                    )
                    Text(
                        "Feels like: $feelsLike",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.End,
                        style = TextStyle(
                            fontSize = 14.sp, color = Color.Gray
                        )
                    )
                    Text(
                        "Wind: $windSpeed km/h",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.End,
                        style = TextStyle(
                            fontSize = 14.sp, color = Color.Gray
                        )
                    )
                    if (cityName.isNotBlank()) {
                        Text(
                            "City name: $cityName",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.End,
                            style = TextStyle(
                                fontSize = 14.sp, color = Color.Gray
                            )
                        )
                    }

                }
            }
        }
    }

}

fun weatherIcon(weatherCondition: String): Int {
    val weatherDrawable = when (weatherCondition.lowercase()) {
        "clear" -> R.drawable.ic_sunny
        "clouds" -> R.drawable.ic_clouds
        "rain" -> R.drawable.ic_rain
        "snow" -> R.drawable.ic_snow
        else -> R.drawable.ic_empty_response // Default icon
    }
    return weatherDrawable
}


