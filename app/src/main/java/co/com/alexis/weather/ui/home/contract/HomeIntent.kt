package co.com.alexis.weather.ui.home.contract

sealed interface HomeIntent {
    data class OnSearch(val query: String) : HomeIntent
    data class OnLocationSelected(val location: String) : HomeIntent
}
