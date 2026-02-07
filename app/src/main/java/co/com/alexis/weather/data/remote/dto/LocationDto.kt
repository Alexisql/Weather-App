package co.com.alexis.weather.data.remote.dto

import co.com.alexis.weather.domain.model.Location
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationDto(
    @SerialName("name") val name: String,
    @SerialName("region") val region: String,
    @SerialName("country") val country: String,
)

fun LocationDto.toDomain() =
    Location(name, region, country)
