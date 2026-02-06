package co.com.alexis.weather.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object WeatherTypography {
    val TitleMediumCard
        @Composable
        get() = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
}