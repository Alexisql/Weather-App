package co.com.alexis.weather.data.remote.dto

import co.com.alexis.weather.domain.model.Day
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DayDto(
    @SerialName("avghumidity") val humidity: Int,
    @SerialName("condition") val condition: ConditionDto,
    @SerialName("maxtemp_c") val tempMax: Double,
    @SerialName("mintemp_c") val tempMin: Double
)

fun DayDto.toDomain() =
    Day(humidity, condition.toDomain(), tempMax, tempMin)