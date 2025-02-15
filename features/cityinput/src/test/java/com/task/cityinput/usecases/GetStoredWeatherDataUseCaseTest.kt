package com.task.cityinput.usecases


import com.task.cityinput.domin.usecase.GetStoredWeatherDataUseCase
import com.task.data.entities.CityWeatherEntity
import com.task.data.repository.RepositoryImpl
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class GetStoredWeatherDataUseCaseTest {

    @Mock
    private lateinit var mockRepositoryImp: RepositoryImpl

    private lateinit var getStoredWeatherDataUseCase: GetStoredWeatherDataUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        getStoredWeatherDataUseCase = GetStoredWeatherDataUseCase(mockRepositoryImp)
    }

    @Test
    fun testGetStoredWeatherUseCase() {
        runTest {
            val mockCityWeatherEntity = CityWeatherEntity(
                id = 1,
                cityName = "San Francisco",
                lon = -122.4194,
                lat = 37.7749,
                feels_like = 18.5,
                temp = 20.0,
                main = "Clear",
                speed = 5.5
            )
            `when`(mockRepositoryImp.getStoredWeather()).thenReturn(
                mockCityWeatherEntity
            )

            val result = getStoredWeatherDataUseCase.invoke()
            assertEquals(
                mockCityWeatherEntity, result
            )
        }
    }
}
