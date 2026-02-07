package co.com.alexis.weather.ui.detail.contract

sealed interface WeatherDetailIntent {
    object OnBack : WeatherDetailIntent
    data class OnChangeTitle(val title: String) : WeatherDetailIntent

}
