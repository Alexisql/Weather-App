package co.com.alexis.weather.ui.detail.contract

sealed interface WeatherDetailEffect {
    data class ShowError(val message: String) : WeatherDetailEffect
    object OnBack : WeatherDetailEffect
}