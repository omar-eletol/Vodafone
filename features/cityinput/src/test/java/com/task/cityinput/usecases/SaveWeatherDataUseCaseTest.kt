package com.task.cityinput.usecases


import com.task.cityinput.domin.usecase.GetWeatherDataUseCase
import com.task.cityinput.domin.usecase.SaveWeatherDataUseCase
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
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class SaveWeatherDataUseCaseTest {

    @Mock
    private lateinit var mockRepositoryImp: RepositoryImpl

    private lateinit var saveWeatherDataUseCase: SaveWeatherDataUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        saveWeatherDataUseCase = SaveWeatherDataUseCase(mockRepositoryImp)
    }

    @Test
    fun testSaveWeatherDataUseCase() = runTest {
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

        `when`(mockRepositoryImp.saveWeather(mockCityWeatherEntity)).thenAnswer { }

        saveWeatherDataUseCase.invoke(mockCityWeatherEntity)

        verify(mockRepositoryImp, times(1)).saveWeather(mockCityWeatherEntity)
    }
}
