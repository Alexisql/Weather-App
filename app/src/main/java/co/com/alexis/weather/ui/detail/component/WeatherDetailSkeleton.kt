package co.com.alexis.weather.ui.detail.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.com.alexis.weather.ui.component.SkeletonComponent

@Composable
fun WeatherDetailSkeleton(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp, start = 32.dp, end = 32.dp),
            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SkeletonComponent(
                width = 160.dp,
                height = 70.dp,
                shape = CircleShape
            )
            SkeletonComponent(
                modifier = Modifier.padding(end = 12.dp),
                width = 75.dp,
                height = 70.dp,
                shape = CircleShape
            )
        }
        HorizontalDivider()
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            (0..3).forEach { _ ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    border = BorderStroke(1.dp, Color.LightGray),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Transparent
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        SkeletonComponent(
                            modifier = Modifier
                                .weight(1f),
                            height = 70.dp
                        )
                        SkeletonComponent(
                            width = 55.dp,
                            height = 50.dp,
                            shape = CircleShape
                        )
                    }
                }
                Spacer(Modifier.height(16.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WeatherDetailSkeletonPreview() {
    WeatherDetailSkeleton()
}