package co.com.alexis.weather.ui.home.contract

sealed interface HomeEffect {
    data class ShowError(val message: String) : HomeEffect
    data class NavigateToDetail(val location: String) : HomeEffect
}