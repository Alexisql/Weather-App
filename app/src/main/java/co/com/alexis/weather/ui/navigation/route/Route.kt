package co.com.alexis.weather.ui.navigation.route

import android.net.Uri

sealed class Route(val route: String) {
    data object Home : Route("Home")
    data object WeatherDetail : Route("detail/{location}") {
        fun createRoute(location: String) = "detail/${Uri.encode(location)}"
    }
}