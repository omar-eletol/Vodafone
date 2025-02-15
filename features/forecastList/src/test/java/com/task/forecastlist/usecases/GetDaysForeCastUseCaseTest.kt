package com.task.forecastlist.usecases


import com.task.data.entities.Coord
import com.task.data.entities.ForeCastListItem
import com.task.data.entities.GetDaysForeCastResponse
import com.task.data.entities.GetWeatherData
import com.task.data.entities.Main
import com.task.data.entities.MainForeCastListItem
import com.task.data.entities.Weather
import com.task.data.entities.WeatherForeCastListItem
import com.task.data.entities.Wind
import com.task.data.entities.WindForeCastListItem
import com.task.data.repository.RepositoryImpl
import com.task.forecastlist.domain.usecase.GetDaysForeCastUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class GetDaysForeCastUseCaseTest {

    @Mock
    private lateinit var mockRepositoryImp: RepositoryImpl

    private lateinit var getDaysForeCastUseCase: GetDaysForeCastUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        getDaysForeCastUseCase = GetDaysForeCastUseCase(mockRepositoryImp)
    }

    @Test
    fun testGetDaysForeCastUseCase() {
        runTest {
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
            `when`(mockRepositoryImp.getDaysForecast(lat = "", lon = "")).thenReturn(
                mockGetDaysForeCastResponse
            )

            val result = getDaysForeCastUseCase.invoke(lat = "", lon = "")
            assertEquals(
                mockGetDaysForeCastResponse, result
            )
        }
    }
}
