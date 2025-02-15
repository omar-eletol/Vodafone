package com.task.forecastlist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.task.core.mainComponents.CustomShimmerEffect
import com.task.core.mainComponents.EmptyView
import com.task.core.mainComponents.MainAppBar
import com.task.core.mainComponents.WeatherCard
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
internal class ForeCastListFragment : Fragment() {

    private val viewModel: ForeCastViewModel by viewModels()
    private val args: ForeCastListFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                Box(Modifier.safeDrawingPadding()) {
                    ForeCastScreen(
                        viewModel = viewModel,
                        onBackClicked = {
                            activity?.onBackPressedDispatcher?.onBackPressed()
                        },
                    )
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.actions.collect { action ->
                when (action) {
                    ForeCastActions.FetchForeCastList -> {
                        getForeCastList(lat = args.lat, lon = args.lon)
                    }
                }
            }
        }
        viewModel.emitAction(ForeCastActions.FetchForeCastList)
    }

    private fun getForeCastList(lat: String, lon: String) {
        viewModel.getForeCastList(lat = lat, lon = lon)
    }


}

@Composable
private fun ForeCastScreen(
    viewModel: ForeCastViewModel,
    onBackClicked: (() -> Unit?)?,

    ) {
    val getForeCastListState by viewModel.getForeCastListState.collectAsState()

    Scaffold(
        modifier = Modifier
            .background(color = Color.White),
        topBar = {
            MainAppBar(title = "Forecast 7 day", onBackClicked = {
                onBackClicked?.invoke()
            })
        },
    ) { innerPadding ->
        Column(Modifier.padding(innerPadding)) {
            ForeCastContent(state = getForeCastListState)
        }


    }

}


@Composable
private fun ForeCastContent(
    state: ForeCastListState,
) {

    Box(
        modifier = Modifier.padding(16.dp)
    ) {
        when (state) {
            is ForeCastListState.Success -> {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                ) {
                    items(state.data?.list?.size ?: 0) { index ->
                        WeatherCard(
                            temperature = state.data?.list?.get(index)?.main?.temp.toString(),
                            mainWeather = state.data?.list?.get(index)?.weather?.firstOrNull()?.main
                                ?: "",
                            feelsLike = state.data?.list?.get(index)?.main?.feels_like.toString(),
                            windSpeed = state.data?.list?.get(index)?.wind?.speed.toString(),
                            cityName = ""
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                    }
                }
            }

            is ForeCastListState.Loading -> {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    items(10) {
                        CustomShimmerEffect()
                    }
                }
            }

            is ForeCastListState.Empty -> {
                EmptyView(title = "There is no weather right now please add your city name")

            }

            is ForeCastListState.Error -> {
                EmptyView(title = "Something went wrong! please try again")
            }

            is ForeCastListState.Idle -> {}
        }
    }
}


@Preview
@Composable
fun Preview(
) {
    ForeCastContent(
        state = ForeCastListState.Idle
    )
}

