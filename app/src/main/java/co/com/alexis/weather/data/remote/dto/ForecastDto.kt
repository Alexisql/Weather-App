package co.com.alexis.weather.data.remote.dto

import co.com.alexis.weather.domain.model.Forecast
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastDto(
    @SerialName("forecastday") val forecastDay: List<ForecastDayDto>
)

fun ForecastDto.toDomain() =
    Forecast(forecastDay.map { it.toDomain() })