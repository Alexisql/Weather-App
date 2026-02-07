package co.com.alexis.weather.ui.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.com.alexis.weather.R
import co.com.alexis.weather.domain.model.Location
import co.com.alexis.weather.ui.component.SpacerComponent
import co.com.alexis.weather.ui.util.WeatherTypography

@Composable
fun ItemLocation(
    modifier: Modifier = Modifier,
    location: Location,
    onItemSelected: (Location) -> Unit,
) {
    Card(
        modifier = modifier
            .padding(vertical = 4.dp, horizontal = 16.dp)
            .clickable {
                onItemSelected(location)
            },
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row {
                Image(
                    modifier = Modifier.size(90.dp),
                    painter = painterResource(R.drawable.weather),
                    contentDescription = null
                )
                SpacerComponent(10)
                Column(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        SpacerComponent(10)
                        Text(
                            text = location.name,
                            style = WeatherTypography.TitleMediumCard,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    SpacerComponent(10)
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        SpacerComponent(10)
                        Text(
                            text = location.country,
                            style = WeatherTypography.TitleMediumCard,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ItemLocationPreview() {
    ItemLocation(
        location = Location("New York", "USA", "USA"),
        onItemSelected = {}
    )
}