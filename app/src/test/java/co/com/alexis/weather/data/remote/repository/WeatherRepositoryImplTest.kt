package co.com.alexis.weather.data.remote.repository

import co.com.alexis.weather.MainDispatcherRule
import co.com.alexis.weather.data.remote.dto.LocationDto
import co.com.alexis.weather.data.remote.dto.WeatherDto
import co.com.alexis.weather.data.remote.service.WeatherService
import co.com.alexis.weather.domain.repository.IWeatherRepository
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherRepositoryImplTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var repository: IWeatherRepository
    private val weatherService: WeatherService = mockk()

    @Before
    fun initBefore() {
        repository = WeatherRepositoryImpl(weatherService, mainDispatcherRule.testDispatcher)
    }

    @Test
    fun `getLocations should return list of locations when service call is successful`() = runTest {
        // Given
        val query = "Bogota"
        val mockDto = listOf(
            LocationDto(
                name = "Bogota",
                region = "Cundinamarca",
                country = "Colombia"
            )
        )
        coEvery { weatherService.getLocations(any(), query) } returns mockDto

        // When
        val result = repository.getLocations(query)

        // Then
        Assert.assertTrue(result.isSuccess)
        Assert.assertEquals("Bogota", result.getOrNull()?.first()?.name)
    }

    @Test
    fun `getLocations should throw exception when service call fails`() = runTest {
        // Given
        val query = "Bogota"
        val exception = IOException("404 Not Found")
        coEvery { weatherService.getLocations(any(), query) } throws exception

        // When
        val result = repository.getLocations(query)

        // Then
        Assert.assertTrue(result.isFailure)
        Assert.assertEquals(exception, result.exceptionOrNull())
    }

    @Test
    fun `getWeather should return success result when service call is successful`() = runTest {
        // Given
        val location = "Bogota"
        val days = 3
        val mockWeatherDto = mockk<WeatherDto>(relaxed = true)
        coEvery { weatherService.getWeather(any(), location, days) } returns mockWeatherDto

        // When
        val result = repository.getWeather(location, days)

        // Then
        Assert.assertTrue(result.isSuccess)
    }

    @Test
    fun `getWeather should return failure result when service call fails`() = runTest {
        // Given
        val location = "Bogota"
        val days = 3
        val exception = IOException("404 Not Found")
        coEvery { weatherService.getWeather(any(), location, days) } throws exception

        // When
        val result = repository.getWeather(location, days)

        // Then
        Assert.assertTrue(result.isFailure)
        Assert.assertEquals(exception, result.exceptionOrNull())
    }
}