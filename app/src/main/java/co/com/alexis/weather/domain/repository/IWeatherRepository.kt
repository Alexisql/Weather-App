package co.com.alexis.weather.domain.repository

import co.com.alexis.weather.domain.model.Location

interface IWeatherRepository {
    suspend fun getLocations(query: String): List<Location>
}