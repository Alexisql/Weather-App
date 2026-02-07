package co.com.alexis.weather.data.remote.util

sealed class WeatherException() : Exception() {
    class NetworkException() : WeatherException() {
        override val message: String =
            "No tienes conexión a internet, por favor revisa tu conexión e intenta de nuevo"
    }

    class ServiceException() : WeatherException() {
        override val message: String =
            "Tenemos problemas con nuestros servidores, por favor intenta más tarde"
    }

    class UnknownException() : WeatherException() {
        override val message: String = "Error desconocido, por favor comunicate con soporte"
    }
}