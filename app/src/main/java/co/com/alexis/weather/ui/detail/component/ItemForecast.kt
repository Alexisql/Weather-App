package co.com.alexis.weather.ui.detail.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.com.alexis.weather.domain.model.Condition
import co.com.alexis.weather.domain.model.Day
import co.com.alexis.weather.domain.model.ForecastDay
import co.com.alexis.weather.ui.component.LoadImageAsyncComponent
import co.com.alexis.weather.ui.theme.Typography
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
                .padding(16.dp),
            text = forecast.date.toDayName(),
            style = Typography.titleMedium
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "${forecast.day.tempMin.toInt()}$GRADE/ ${forecast.day.tempMax.toInt()}$GRADE",
                    fontSize = 30.sp
                )
                Text(
                    text = forecast.day.condition.text
                )
            }
            LoadImageAsyncComponent(
                modifier = Modifier.weight(1f),
                url = forecast.day.condition.icon
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