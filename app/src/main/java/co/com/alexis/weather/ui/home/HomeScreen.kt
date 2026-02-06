package co.com.alexis.weather.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import co.com.alexis.weather.domain.model.Location
import co.com.alexis.weather.ui.component.EmptyContentComponent
import co.com.alexis.weather.ui.component.SearchComponent
import co.com.alexis.weather.ui.component.SpacerComponent

@Composable
fun HomeScreen(
    modifier: Modifier
) {
    HomeContent(
        modifier = modifier,
        query = "",
        locations = listOf()
    )
}

@Composable
private fun HomeContent(
    modifier: Modifier = Modifier,
    query: String,
    locations: List<Location>
) {
    Scaffold(
        modifier = modifier
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            SearchComponent(
                query = query,
                onQueryChange = { }
            )
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
            ) {
                items(
                    count = locations.size
                ) { index ->
                    val location = locations[index]
                    SpacerComponent(10)
                    ItemLocation(
                        location = location,
                        onItemSelected = { }
                    )
                }
                if (locations.isEmpty()) {
                    item {
                        EmptyContentComponent()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeContentPreview() {
    HomeContent(
        query = "",
        locations = listOf()
    )
}