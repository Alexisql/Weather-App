package co.com.alexis.weather.data.remote.dto

import co.com.alexis.weather.domain.model.Condition
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

private const val PROTOCOL_HTTPS = "https:"

@Serializable
data class ConditionDto(
    @SerialName("icon") val icon: String,
    @SerialName("text") val text: String
)

fun ConditionDto.toDomain() =
    Condition(PROTOCOL_HTTPS.plus(icon), text)