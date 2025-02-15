package com.task.cityinput.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.task.cityinput.R
import com.task.cityinput.presentation.components.EditTextWithButton
import com.task.core.mainComponents.CustomShimmerEffect
import com.task.core.mainComponents.EmptyView
import com.task.core.mainComponents.MainAppBar
import com.task.core.mainComponents.WeatherCard
import com.task.core.theme.Navy80
import com.task.core.utils.NavigationUtil.navigateTo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class CityInputFragment : Fragment() {

    private val viewModel: CityInputViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                Box(Modifier.safeDrawingPadding()) {
                    CityInputScreen(
                        viewModel = viewModel,
                        onBackClicked = {
                            activity?.onBackPressedDispatcher?.onBackPressed()
                        },
                        onButtonClicked = { cityName ->
                            convertCityName(cityName = cityName);
                        },
                        showForecastClicked = { lat, lon ->
                            Log.e("showForecastClicked", "showForecastClicked: " + lat)
                            navigateTo(
                                com.task.core.R.id.action_cityInputFragment_to_foreCastListFragment,
                                args = Bundle().apply {
                                    putString("lat", lat)
                                    putString("lon", lon)
                                },
                            )
                        }
                    )
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getStoredWeatherData()
    }


    private fun convertCityName(cityName: String) {
        viewModel.convertCityName(cityName = cityName)
    }


}

@Composable
private fun CityInputScreen(
    viewModel: CityInputViewModel,
    onBackClicked: (() -> Unit?)?,
    onButtonClicked: ((String) -> Unit)?,
    showForecastClicked: ((String, String) -> Unit)?

) {
    val getWeatherDataState by viewModel.getWeatherDataState.collectAsState()
    val convertCityNameState by viewModel.convertCityNameState.collectAsState()

    val focusManager = LocalFocusManager.current

    Scaffold(
        modifier = Modifier
            .background(color = Color.White)
            .pointerInput(Unit) {
                detectTapGestures { focusManager.clearFocus() }
            },

        topBar = {
            MainAppBar(title = "Current weather", onBackClicked = {
                onBackClicked?.invoke()
            })
        },
    ) { innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            EditTextWithButton(
                onButtonClicked = { text ->
                    onButtonClicked?.invoke(text)
                },
                focusManager = focusManager,
                getWeatherDataState = getWeatherDataState,
                convertCityNameState = convertCityNameState
            )
            Spacer(modifier = Modifier.height(20.dp))
            CityInputContent(
                getWeatherDataState = getWeatherDataState,
                convertCityNameState = convertCityNameState,
                showForecastClicked = { lat, lon ->
                    showForecastClicked?.invoke(lat, lon)

                }
            )


        }


    }

}

@Composable
private fun CityInputContent(
    getWeatherDataState: GetWeatherDataState,
    convertCityNameState: ConvertCityNameState,
    showForecastClicked: ((String, String) -> Unit)?

) {
    when (convertCityNameState) {
        ConvertCityNameState.Empty -> {
            EmptyView(title = "There is no weather right now please add your city name")
        }

        is ConvertCityNameState.Error -> {
            EmptyView(title = "Something went wrong! please try again")

        }

        ConvertCityNameState.Idle -> {
            EmptyView(title = "There is no weather right now please add your city name")
        }

        ConvertCityNameState.Loading -> {
            CustomShimmerEffect()
        }

        is ConvertCityNameState.Success -> {
            HandleWeatherDataState(
                getWeatherDataState = getWeatherDataState,
                showForecastClicked = { lat, lon ->
                    showForecastClicked?.invoke(lat, lon)
                },
            )
        }
    }
}


@Composable
private fun HandleWeatherDataState(
    getWeatherDataState: GetWeatherDataState,
    showForecastClicked: ((String, String) -> Unit)?

) {
    when (getWeatherDataState) {
        is GetWeatherDataState.Success -> {
            Text(
                "Show Forecast 7 day",
                modifier = Modifier.clickable {
                    showForecastClicked?.invoke(
                        getWeatherDataState.data.coord.lat.toString(),
                        getWeatherDataState.data.coord.lon.toString(),
                    )
                },
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.End,
                style = TextStyle(
                    fontSize = 14.sp, color = Navy80,
                    fontWeight = FontWeight(700)
                )

            )
            Spacer(modifier = Modifier.height(10.dp))
            WeatherCard(
                temperature = getWeatherDataState.data.main.temp.toString(),
                mainWeather = getWeatherDataState.data.weather.firstOrNull()?.main ?: "",
                feelsLike = getWeatherDataState.data.main.feels_like.toString(),
                windSpeed = getWeatherDataState.data.wind.speed.toString(),
                cityName = getWeatherDataState.data.name,
            )
        }

        is GetWeatherDataState.Loading -> {
            CustomShimmerEffect()

        }

        is GetWeatherDataState.Empty -> {
            EmptyView(title = "There is no weather right now please add your city name")

        }

        is GetWeatherDataState.Error -> {
            EmptyView(title = "Something went wrong! please try again")

        }

        is GetWeatherDataState.Idle -> {
            EmptyView(title = "There is no weather right now please add your city name")
        }
    }

}


@Preview
@Composable
fun Preview(
) {
    HandleWeatherDataState(
        getWeatherDataState = GetWeatherDataState.Idle,
        showForecastClicked = null
    )
}

