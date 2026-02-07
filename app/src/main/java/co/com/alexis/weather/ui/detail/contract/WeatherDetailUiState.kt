package co.com.alexis.weather.ui.detail.contract

import co.com.alexis.weather.domain.model.Weather

sealed interface WeatherDetailUiState {
    data object Idle : WeatherDetailUiState
    data object Loading : WeatherDetailUiState
    data class Success(val weather: Weather) : WeatherDetailUiState
}