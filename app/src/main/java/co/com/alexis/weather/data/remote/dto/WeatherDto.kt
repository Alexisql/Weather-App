package co.com.alexis.weather.data.remote.dto

import co.com.alexis.weather.domain.model.Weather
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherDto(
    @SerialName("location") val location: LocationDto,
    @SerialName("current") val current: CurrentDto,
    @SerialName("forecast") val forecast: ForecastDto
)

fun WeatherDto.toDomain() =
    Weather(location.toDomain(), current.toDomain(), forecast.toDomain())