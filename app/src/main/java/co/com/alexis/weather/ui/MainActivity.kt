package co.com.alexis.weather.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import co.com.alexis.weather.ui.component.ErrorHandlerContext
import co.com.alexis.weather.ui.home.HomeScreen
import co.com.alexis.weather.ui.theme.WeatherTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition { false }
        enableEdgeToEdge()
        setContent {
            WeatherTheme {
                ErrorHandlerContext {
                    HomeScreen(
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}