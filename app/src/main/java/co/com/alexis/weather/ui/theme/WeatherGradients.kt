package co.com.alexis.weather.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush

object WeatherGradients {
    val MainBackground
        @Composable get() = Brush.verticalGradient(
            colors = listOf(DarkBlue, BrightBlue, LightBlue)
        )
}