package co.com.alexis.weather.ui.detail.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.com.alexis.weather.R
import co.com.alexis.weather.domain.model.Condition
import co.com.alexis.weather.domain.model.Current
import co.com.alexis.weather.domain.model.Day
import co.com.alexis.weather.domain.model.Forecast
import co.com.alexis.weather.domain.model.ForecastDay
import co.com.alexis.weather.domain.model.Location
import co.com.alexis.weather.domain.model.Weather
import co.com.alexis.weather.ui.util.WeatherTypography

private const val CENTIGRADE = "°C"

@Composable
fun WeatherDetailSuccess(
    weather: Weather
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "${weather.current.temp.toInt()} $CENTIGRADE ",
                    fontWeight = FontWeight.Companion.Bold,
                    fontSize = 50.sp
                )
                Text(
                    text = weather.current.condition.text,
                    style = WeatherTypography.TitleMediumCard
                )
            }
            Image(
                modifier = Modifier.size(150.dp),
                painter = painterResource(id = R.drawable.ic_splash),
                contentDescription = null
            )
        }

        HorizontalDivider(modifier = Modifier.padding(bottom = 8.dp))

        LazyColumn {
            items(weather.forecast.forecastDay) { forecast ->
                ItemForecast(forecast)
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun WeatherDetailSuccessPreview() {
    WeatherDetailSuccess(
        weather = Weather(
            location = Location(
                name = "Bogota",
                region = "Cundinamarca",
                country = "Colombia"
            ),
            current = Current(
                condition = Condition("", "Lluvioso"),
                humidity = 0,
                temp = 24.0,
                wind = 0.0
            ),
            forecast = Forecast(
                listOf(
                    ForecastDay(
                        "2026-02-07", Day(
                            0, Condition("", "Lluvioso"), 25.0, 22.0
                        )
                    ),
                    ForecastDay(
                        "2026-02-07", Day(
                            0, Condition("", "Lluvioso"), 25.0, 22.0
                        )
                    )
                )
            )
        )
    )
}