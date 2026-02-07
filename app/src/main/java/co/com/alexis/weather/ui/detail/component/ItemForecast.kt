package co.com.alexis.weather.ui.detail.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import co.com.alexis.weather.domain.model.Day
import co.com.alexis.weather.domain.model.ForecastDay
import co.com.alexis.weather.ui.util.WeatherTypography
import co.com.alexis.weather.ui.util.toDayName

private const val GRADE = "°"

@Composable
fun ItemForecast(
    forecast: ForecastDay
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            text = forecast.date.toDayName(),
            style = WeatherTypography.TitleMediumCard
        )
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
                    text = "${forecast.day.tempMin.toInt()}$GRADE/ ${forecast.day.tempMax.toInt()}$GRADE",
                    fontWeight = FontWeight.Companion.Bold,
                    fontSize = 30.sp
                )
                Text(
                    text = forecast.day.condition.text,
                    style = WeatherTypography.TitleMediumCard
                )
            }
            Image(
                modifier = Modifier.size(150.dp),
                painter = painterResource(id = R.drawable.ic_splash),
                contentDescription = null
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ItemForecastPreview() {
    ItemForecast(
        forecast = ForecastDay(
            date = "2026-02-07",
            day = Day(0, Condition("", "Lluvioso"), 25.0, 22.0)
        )
    )
}