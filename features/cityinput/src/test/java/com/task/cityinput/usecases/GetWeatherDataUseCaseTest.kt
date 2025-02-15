package com.task.cityinput.usecases


import com.task.cityinput.domin.usecase.GetWeatherDataUseCase
import com.task.data.entities.CityWeatherEntity
import com.task.data.entities.Coord
import com.task.data.entities.GetWeatherData
import com.task.data.entities.Main
import com.task.data.entities.Weather
import com.task.data.entities.Wind
import com.task.data.repository.RepositoryImpl
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class GetWeatherDataUseCaseTest {

    @Mock
    private lateinit var mockRepositoryImp: RepositoryImpl

    private lateinit var getWeatherDataUseCase: GetWeatherDataUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        getWeatherDataUseCase = GetWeatherDataUseCase(mockRepositoryImp)
    }

    @Test
    fun testGetWeatherDataUseCase() {
        runTest {
            val mockGetWeatherData = GetWeatherData(
                id = 12345,
                coord = Coord(lon = 37.7749, lat = -122.4194),
                main = Main(feels_like = 18.5, temp = 20.0),
                name = "San Francisco",
                weather = listOf(Weather(main = "Clear")),
                wind = Wind(speed = 5.5)
            )
            `when`(mockRepositoryImp.getWeatherData(lat = "", lon = "")).thenReturn(
                mockGetWeatherData
            )

            val result = getWeatherDataUseCase.invoke(lat = "", lon = "")
            assertEquals(
                mockGetWeatherData, result
            )
        }
    }
}
