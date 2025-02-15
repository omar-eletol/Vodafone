package com.task.data.repo


import com.task.data.datasource.database.WeatherDao
import com.task.data.datasource.network.EndPoint.APP_ID
import com.task.data.datasource.network.service.ApiService
import com.task.data.entities.CityWeatherEntity
import com.task.data.entities.ConvertCityNameResponseItem
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
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.doNothing
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class MainRepoTest {

    @Mock
    private lateinit var mockApiService: ApiService

    @Mock
    private lateinit var mockWeatherDao: WeatherDao

    private lateinit var homeRepositoryImp: RepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        homeRepositoryImp = RepositoryImpl(mockApiService, mockWeatherDao)
    }

    @Test
    fun testConvertCityName() {
        runTest {
            val mockConvertCityNameResponseItem =
                ConvertCityNameResponseItem(lat = 3.4846, lon = 72.8776)

            `when`(mockApiService.convertCityName("dd")).thenReturn(
                listOf(
                    mockConvertCityNameResponseItem
                )
            )

            val result = homeRepositoryImp.convertCityName("dd")

            assertEquals(listOf(mockConvertCityNameResponseItem), result)
        }
    }

    @Test
    fun testGetWeatherData() {
        runTest {
            val mockGetWeatherData = GetWeatherData(
                id = 12345,
                coord = Coord(lon = 37.7749, lat = -122.4194),
                main = Main(feels_like = 18.5, temp = 20.0),
                name = "San Francisco",
                weather = listOf(Weather(main = "Clear")),
                wind = Wind(speed = 5.5)
            )
            `when`(
                mockApiService.getWeatherData(
                    lat = "37.7749", lon = "-122.4194"
                )
            ).thenReturn(mockGetWeatherData)

            val result = homeRepositoryImp.getWeatherData(lat = "37.7749", lon = "-122.4194")

            assertEquals(mockGetWeatherData, result)
        }
    }

    @Test
    fun testGetDaysForecast() {
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
            `when`(
                mockApiService.getDaysForecast(
                    lat = "37.7749", lon = "-122.4194"
                )
            ).thenReturn(mockGetDaysForeCastResponse)

            val result = homeRepositoryImp.getDaysForecast(lat = "37.7749", lon = "-122.4194")

            assertEquals(mockGetDaysForeCastResponse, result)
        }
    }


    @Test
    fun testGetStoredWeather() {
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

            `when`(mockWeatherDao.getStoredWeather()).thenReturn(mockCityWeatherEntity)

            val result = homeRepositoryImp.getStoredWeather()

            assertEquals(mockCityWeatherEntity, result)
        }
    }

    @Test
    fun testSaveWeather() = runTest {
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

        homeRepositoryImp.saveWeather(mockCityWeatherEntity)

        verify(mockWeatherDao, times(1)).replaceWeather(mockCityWeatherEntity)
    }


}

