package com.task.cityinput.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.unit.dp
import com.task.cityinput.presentation.ConvertCityNameState
import com.task.cityinput.presentation.GetWeatherDataState
import com.task.core.theme.Navy80

@Composable
fun EditTextWithButton(
    onButtonClicked: ((String) -> Unit),
    focusManager: FocusManager,
    getWeatherDataState: GetWeatherDataState,
    convertCityNameState: ConvertCityNameState,
) {
    var text by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            maxLines = 1,
            value = text,
            onValueChange = { text = it },
            modifier = Modifier.weight(1f),
            label = { Text("city name,country code") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Navy80,
                focusedLabelColor = Navy80,
            ),
        )
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Navy80,
            ),
            onClick = {
                focusManager.clearFocus()
                onButtonClicked?.invoke(text)
            },
            enabled = text.isNotEmpty() && convertCityNameState != ConvertCityNameState.Loading && getWeatherDataState != GetWeatherDataState.Loading
        ) {
            Text("Search")
        }

    }
}
