package co.com.alexis.weather.ui.home.contract

import co.com.alexis.weather.domain.model.Location

sealed interface HomeUiState {
    data object Idle : HomeUiState
    data object Loading : HomeUiState
    data class Success(val locations: List<Location>) : HomeUiState
}