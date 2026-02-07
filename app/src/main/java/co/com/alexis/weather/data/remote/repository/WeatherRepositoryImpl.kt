package co.com.alexis.weather.data.remote.repository

import co.com.alexis.weather.BuildConfig.API_KEY
import co.com.alexis.weather.data.remote.dto.toDomain
import co.com.alexis.weather.data.remote.service.WeatherService
import co.com.alexis.weather.data.remote.util.safeApiCall
import co.com.alexis.weather.domain.model.Location
import co.com.alexis.weather.domain.model.Weather
import co.com.alexis.weather.domain.repository.IWeatherRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

private const val CLAZZ = "WeatherRepositoryImpl"

class WeatherRepositoryImpl @Inject constructor(
    private val weatherService: WeatherService,
    private val dispatcherIO: CoroutineDispatcher
) : IWeatherRepository {

    override suspend fun getLocations(query: String): Result<List<Location>> =
        withContext(dispatcherIO) {
            safeApiCall(CLAZZ) {
                val response = weatherService.getLocations(API_KEY, query)
                response.map { it.toDomain() }
            }
        }

    override suspend fun getWeather(location: String, numberDays: Int): Result<Weather> =
        withContext(dispatcherIO) {
            safeApiCall(CLAZZ) {
                val response = weatherService.getWeather(API_KEY, location, numberDays)
                response.toDomain()
            }
        }

}