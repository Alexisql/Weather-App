package co.com.alexis.weather.domain.repository

import co.com.alexis.weather.domain.model.Location
import co.com.alexis.weather.domain.model.Weather

interface IWeatherRepository {
    suspend fun getLocations(query: String): Result<List<Location>>
    suspend fun getWeather(location: String, numberDays: Int): Result<Weather>
}