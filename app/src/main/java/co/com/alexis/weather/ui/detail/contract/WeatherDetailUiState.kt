package co.com.alexis.weather.ui.detail.contract

import co.com.alexis.weather.domain.model.Weather

sealed interface WeatherDetailUiState {
    object Idle : WeatherDetailUiState
    object Loading : WeatherDetailUiState
    data class Success(val weather: Weather) : WeatherDetailUiState
}