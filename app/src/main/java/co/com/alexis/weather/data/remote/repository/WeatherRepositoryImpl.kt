package co.com.alexis.weather.data.remote.repository

import co.com.alexis.weather.domain.model.Location
import co.com.alexis.weather.domain.repository.IWeatherRepository
import kotlinx.coroutines.delay
import java.io.IOException
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
) : IWeatherRepository {

    override suspend fun getLocations(query: String): List<Location> {
        return try {
            delay(2000)
            listOf(
                Location("Cucuta", "Norte de Santander", "Colombia"),
                Location("Los Patios", "Norte de Santander", "Colombia"),
                Location("Villa del Rosario", "Norte de Santander", "Colombia"),
                Location("Zulia", "Norte de Santander", "Colombia")
            )
        } catch (exception: IOException) {
            throw Exception(exception)
        }
    }
}