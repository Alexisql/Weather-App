package co.com.alexis.weather.data.remote.dto

import co.com.alexis.weather.domain.model.Current
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentDto(
    @SerialName("condition") val condition: ConditionDto,
    @SerialName("humidity") val humidity: Int,
    @SerialName("temp_c") val temp: Double,
    @SerialName("wind_kph") val wind: Double
)

fun CurrentDto.toDomain() =
    Current(condition.toDomain(), humidity, temp, wind)