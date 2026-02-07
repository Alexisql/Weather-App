package co.com.alexis.weather.data.remote.repository

import co.com.alexis.weather.BuildConfig.API_KEY
import co.com.alexis.weather.data.remote.dto.toDomain
import co.com.alexis.weather.data.remote.service.WeatherService
import co.com.alexis.weather.domain.model.Location
import co.com.alexis.weather.domain.model.Weather
import co.com.alexis.weather.domain.repository.IWeatherRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherService: WeatherService,
    private val dispatcherIO: CoroutineDispatcher
) : IWeatherRepository {

    override suspend fun getLocations(query: String): List<Location> {
        return try {
            withContext(dispatcherIO) {
                val response = weatherService.getLocations(API_KEY, query)
                response.map { it.toDomain() }
            }
        } catch (exception: IOException) {
            throw Exception(exception)
        }
    }

    override suspend fun getWeather(location: String, numberDays: Int): Result<Weather> {
        return try {
            withContext(dispatcherIO) {
                val response = weatherService.getWeather(API_KEY, location, numberDays)
                Result.success(response.toDomain())
            }
        } catch (exception: IOException) {
            Result.failure(exception)

        }
    }
}