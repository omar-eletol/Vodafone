package com.task.cityinput.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.task.core.R
import com.task.core.mainComponents.CustomShimmerEffect
import com.task.core.mainComponents.EmptyView
import com.task.core.mainComponents.MainAppBar
import com.task.core.mainComponents.WeatherCard
import com.task.core.theme.Navy80
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class CityInputFragment : Fragment() {

    private val viewModel: ChallengesHistoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                Box(Modifier.safeDrawingPadding()) {
                    ChallengesHistoryScreen(
                        viewModel = viewModel,
                        onBackClicked = {
                            activity?.onBackPressedDispatcher?.onBackPressed()
                        },
                        onButtonClicked = { cityName ->
                            convertCityName(cityName = cityName);
                        },
                    )
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        viewLifecycleOwner.lifecycleScope.launch {
//            viewModel.actions.collect { action ->
//                when (action) {
//                    ChallengesHistoryActions.FetchPointsToBeExpiredList -> fetchPointsToBeExpiredList()
//                    ChallengesHistoryActions.SwipeRefreshCalled -> fetchPointsToBeExpiredList()
//                }
//            }
//        }
//        viewModel.emitAction(ChallengesHistoryActions.FetchPointsToBeExpiredList)
    }

    private fun convertCityName(cityName: String) {
        viewModel.convertCityName(cityName = cityName)
    }


}

@Composable
private fun ChallengesHistoryScreen(
    viewModel: ChallengesHistoryViewModel,
    onBackClicked: (() -> Unit?)?,
    onButtonClicked: ((String) -> Unit)?

) {
    val getWeatherDataState by viewModel.getWeatherDataState.collectAsState()
    val convertCityNameState by viewModel.convertCityNameState.collectAsState()

    var text by remember { mutableStateOf("") }
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
        Column(Modifier.padding(horizontal = 16.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding),
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
            Spacer(modifier = Modifier.height(20.dp))
            CityInputContent(
                getWeatherDataState = getWeatherDataState,
                convertCityNameState = convertCityNameState,
            )


        }


    }

}

@Composable
private fun CityInputContent(
    getWeatherDataState: GetWeatherDataState,
    convertCityNameState: ConvertCityNameState,

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
            GetWeatherDataState(getWeatherDataState = getWeatherDataState)
        }
    }
}


@Composable
private fun GetWeatherDataState(
    getWeatherDataState: GetWeatherDataState,

    ) {
    when (getWeatherDataState) {
        is GetWeatherDataState.Success -> {
            WeatherCard(
                temperature = getWeatherDataState.data.main.temp.toString(),
                mainWeather = getWeatherDataState.data.weather.firstOrNull()?.main ?: "",
                feelsLike = getWeatherDataState.data.main.feels_like.toString(),
                windSpeed = getWeatherDataState.data.wind.speed.toString(),
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


//@Composable
//private fun ChallengesHistoryContent(
//    state: ChallengesHistoryState, onItemClicked: (() -> Unit?)?
//) {
//
//    Box(
//        modifier = Modifier.padding(16.dp)
//    ) {
//        when (state) {
//            is ChallengesHistoryState.Success -> {
//                LazyColumn {
//                    items(state.data) { item ->
//                        TransactionsItem(item = item) { onItemClicked?.invoke() }
//                    }
//                }
//            }
//
//            is ChallengesHistoryState.Loading -> {}
//            is ChallengesHistoryState.Empty -> {
//                LazyColumn(Modifier.fillMaxSize()) {
//                    item {
//                        Text(
//                            text = "EMPTY LIST",
//                            modifier = Modifier.align(Alignment.Center),
//                        )
//                    }
//                }
//            }
//
//            is ChallengesHistoryState.Error -> {
//                LazyColumn(Modifier.fillMaxSize()) {
//                    item {
//                        Text(
//                            text = "ERROR SCREEN",
//                            modifier = Modifier.align(Alignment.Center),
//                        )
//                    }
//                }
//            }
//
//            is ChallengesHistoryState.Idle -> {
////                val item = HistoryResponse(
////                    id = "1",
////                    challengeName = "safda",
////                    rewardsCount = "2",
////                    points = "200",
////                    vouchers = "3",
////                    dummy1 = "200",
////                    dummy2 = "200",
////                    dummy3 = "200",
////                )
////                LazyColumn {
////                    items(5) {
////                        TransactionsItem(item = item, onItemClicked = { onItemClicked?.invoke() })
////                    }
////                }
//            }
//        }
//    }
//}


@Preview
@Composable
fun Preview(
) {
//    ChallengesHistoryContent(
//        state = ChallengesHistoryState.Idle, onItemClicked = null
//    )
}

