package co.com.alexis.weather.data.remote.dto

import co.com.alexis.weather.domain.model.ForecastDay
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastDayDto(
    @SerialName("date") val date: String,
    @SerialName("day") val day: DayDto
)

fun ForecastDayDto.toDomain() =
    ForecastDay(date, day.toDomain())