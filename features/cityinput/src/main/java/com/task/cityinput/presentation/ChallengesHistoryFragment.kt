package com.task.cityinput.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.task.cityinput.presentation.components.TransactionsItem
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.task.core.R
import com.task.core.mainComponents.MainAppBar
import com.task.core.utils.NavigationUtil.navigateTo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
internal class TransactionsFragment : Fragment() {

    private val viewModel: ChallengesHistoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                Box(Modifier.safeDrawingPadding()) {
                    ChallengesHistoryScreen(viewModel = viewModel, onBackClicked = {
                        activity?.onBackPressedDispatcher?.onBackPressed()
                    }, onItemClicked = {
                        navigateTo(id = R.id.ChallengeDetailsBottomSheet)
                    })
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.actions.collect { action ->
                when (action) {
                    ChallengesHistoryActions.FetchPointsToBeExpiredList -> fetchPointsToBeExpiredList()
                    ChallengesHistoryActions.SwipeRefreshCalled -> fetchPointsToBeExpiredList()
                }
            }
        }
        viewModel.emitAction(ChallengesHistoryActions.FetchPointsToBeExpiredList)
    }

    private fun fetchPointsToBeExpiredList() {
        viewModel.fetchHistoryList()
    }

}

@Composable
private fun ChallengesHistoryScreen(
    viewModel: ChallengesHistoryViewModel,
    onBackClicked: (() -> Unit?)?,
    onItemClicked: (() -> Unit?)?

) {
    val state by viewModel.state.collectAsState()
    Scaffold(
        modifier = Modifier.background(color = Color.White),
        topBar = {
            MainAppBar(title = "History", onBackClicked = {
                onBackClicked?.invoke()
            })
        },
    ) { innerPadding ->

        SwipeRefresh(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            state = rememberSwipeRefreshState(isRefreshing = state == ChallengesHistoryState.Loading),
            onRefresh = { viewModel.emitAction(ChallengesHistoryActions.SwipeRefreshCalled) },
        ) {
            HorizontalDivider()
            ChallengesHistoryContent(state = state, onItemClicked = { onItemClicked?.invoke() })
        }

    }

}

@Composable
private fun ChallengesHistoryContent(
    state: ChallengesHistoryState, onItemClicked: (() -> Unit?)?
) {

    Box(
        modifier = Modifier.padding(16.dp)
    ) {
        when (state) {
            is ChallengesHistoryState.Success -> {
                LazyColumn {
                    items(state.data) { item ->
                        TransactionsItem(item = item) { onItemClicked?.invoke() }
                    }
                }
            }

            is ChallengesHistoryState.Loading -> {}
            is ChallengesHistoryState.Empty -> {
                LazyColumn(Modifier.fillMaxSize()) {
                    item {
                        Text(
                            text = "EMPTY LIST",
                            modifier = Modifier.align(Alignment.Center),
                        )
                    }
                }
            }

            is ChallengesHistoryState.Error -> {
                LazyColumn(Modifier.fillMaxSize()) {
                    item {
                        Text(
                            text = "ERROR SCREEN",
                            modifier = Modifier.align(Alignment.Center),
                        )
                    }
                }
            }

            is ChallengesHistoryState.Idle -> {
//                val item = HistoryResponse(
//                    id = "1",
//                    challengeName = "safda",
//                    rewardsCount = "2",
//                    points = "200",
//                    vouchers = "3",
//                    dummy1 = "200",
//                    dummy2 = "200",
//                    dummy3 = "200",
//                )
//                LazyColumn {
//                    items(5) {
//                        TransactionsItem(item = item, onItemClicked = { onItemClicked?.invoke() })
//                    }
//                }
            }
        }
    }
}


@Preview
@Composable
fun Preview(
) {
    ChallengesHistoryContent(
        state = ChallengesHistoryState.Idle, onItemClicked = null
    )
}

