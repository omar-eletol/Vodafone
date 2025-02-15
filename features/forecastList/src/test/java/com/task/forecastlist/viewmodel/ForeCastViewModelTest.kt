package com.task.forecastlist.viewmodel

import app.cash.turbine.test
import com.task.data.entities.ForeCastListItem
import com.task.data.entities.GetDaysForeCastResponse
import com.task.data.entities.MainForeCastListItem
import com.task.data.entities.WeatherForeCastListItem
import com.task.data.entities.WindForeCastListItem
import com.task.forecastlist.domain.usecase.GetDaysForeCastUseCase
import com.task.forecastlist.presentation.ForeCastActions
import com.task.forecastlist.presentation.ForeCastListState
import com.task.forecastlist.presentation.ForeCastViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

@ExperimentalCoroutinesApi
class ForeCastViewModelTest {

    private lateinit var getDaysForeCastUseCase: GetDaysForeCastUseCase

    private lateinit var foreCastViewModel: ForeCastViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        getDaysForeCastUseCase = mock(GetDaysForeCastUseCase::class.java)

        Dispatchers.setMain(testDispatcher)
        foreCastViewModel = ForeCastViewModel(getDaysForeCastUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun `getForeCastList emits Loading then Success when API returns data`() = runTest {
        val mockGetDaysForeCastResponse = GetDaysForeCastResponse(
            list = listOf(
                ForeCastListItem(
                    dt_txt = "2025-02-15 12:00:00", main = MainForeCastListItem(
                        feels_like = 16.5, temp = 18.0, temp_max = 20.0, temp_min = 15.0
                    ), weather = listOf(
                        WeatherForeCastListItem(
                            description = "Sunny", icon = "01d", id = 800, main = "Clear"
                        )
                    ), wind = WindForeCastListItem(
                        deg = 180, gust = 8.5, speed = 5.0
                    )
                ), ForeCastListItem(
                    dt_txt = "2025-02-16 12:00:00", main = MainForeCastListItem(
                        feels_like = 14.5, temp = 16.0, temp_max = 18.0, temp_min = 13.0
                    ), weather = listOf(
                        WeatherForeCastListItem(
                            description = "Cloudy", icon = "02d", id = 801, main = "Clouds"
                        )
                    ), wind = WindForeCastListItem(
                        deg = 200, gust = 7.0, speed = 4.0
                    )
                )
            ), message = 0
        )
        val mockResponse = ForeCastListState.Success(data = mockGetDaysForeCastResponse)
        `when`(getDaysForeCastUseCase.invoke(lat = "", lon = "")).thenReturn(
            mockGetDaysForeCastResponse
        )

        foreCastViewModel.getForeCastList(lat = "", lon = "")

        foreCastViewModel.getForeCastListState.test {
            val secondState = awaitItem()
            assertEquals(mockResponse, secondState)
            cancelAndConsumeRemainingEvents()
        }
    }


    @Test
    fun `emitAction sends correct action to channel`() = runTest {
        val testAction = ForeCastActions.FetchForeCastList

        foreCastViewModel.emitAction(testAction)

        foreCastViewModel.actions.test {
            assertEquals(testAction, awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }
}
