package co.com.alexis.weather.data.remote.util

sealed class WeatherException() : Exception() {
    class NetworkException() : WeatherException() {
        override val message: String =
            "You don't have an internet connection, please check your connection and try again."
    }

    class ServiceException() : WeatherException() {
        override val message: String = "We're having server issues, please try again later."
    }

    class UnknownException() : WeatherException() {
        override val message: String = "Unknown error, please contact support"
    }
}