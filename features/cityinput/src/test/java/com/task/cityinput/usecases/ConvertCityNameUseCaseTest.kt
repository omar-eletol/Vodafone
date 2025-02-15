package com.task.cityinput.usecases


import com.task.cityinput.domin.usecase.ConvertCityNameUseCase
import com.task.data.entities.ConvertCityNameResponseItem
import com.task.data.repository.RepositoryImpl
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class ConvertCityNameUseCaseTest {

    @Mock
    private lateinit var mockRepositoryImp: RepositoryImpl

    private lateinit var getConvertCityNameUseCase: ConvertCityNameUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        getConvertCityNameUseCase = ConvertCityNameUseCase(mockRepositoryImp)
    }

    @Test
    fun testConvertCityNameUseCase() {
        runTest {
            val mockConvertCityNameResponseItem =
                ConvertCityNameResponseItem(lat = 3.4846, lon = 72.8776)

            `when`(mockRepositoryImp.convertCityName("")).thenReturn(
                listOf(
                    mockConvertCityNameResponseItem
                )
            )

            val result = getConvertCityNameUseCase.invoke("")
            assertEquals(
                listOf(
                    mockConvertCityNameResponseItem
                ), result
            )
        }
    }
}
