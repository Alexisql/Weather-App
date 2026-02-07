package co.com.alexis.weather.domain.model

data class Current(
    val condition: Condition,
    val humidity: Int,
    val temp: Double,
    val wind: Double
)