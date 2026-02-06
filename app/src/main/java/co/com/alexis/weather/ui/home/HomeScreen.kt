package co.com.alexis.weather.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import co.com.alexis.weather.domain.model.Location
import co.com.alexis.weather.ui.component.EmptyContentComponent
import co.com.alexis.weather.ui.component.ErrorDialog
import co.com.alexis.weather.ui.component.LocalErrorHandler
import co.com.alexis.weather.ui.home.component.SearchComponent
import co.com.alexis.weather.ui.component.SpacerComponent
import co.com.alexis.weather.ui.home.component.HomeSkeleton
import co.com.alexis.weather.ui.home.component.ItemLocation
import co.com.alexis.weather.ui.home.contract.HomeEffect
import co.com.alexis.weather.ui.home.contract.HomeIntent
import co.com.alexis.weather.ui.home.contract.HomeUiState

@Composable
fun HomeScreen(
    modifier: Modifier,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val state by homeViewModel.uiState.collectAsStateWithLifecycle()
    val query by homeViewModel.searchQuery.collectAsStateWithLifecycle()
    val locations = homeViewModel.locations.collectAsStateWithLifecycle()
    val errorHandler = LocalErrorHandler.current

    LaunchedEffect(Unit) {
        homeViewModel.effects.collect { effect ->
            when (effect) {
                is HomeEffect.ShowError -> {
                    errorHandler.showError(ErrorDialog(effect.message))
                }

                HomeEffect.NavigateToDetail -> {

                }
            }
        }
    }

    HomeContent(
        modifier = modifier,
        state = state,
        query = query,
        locations = locations.value,
        onIntent = { intent ->
            when (intent) {
                is HomeIntent.OnSearch -> {
                    homeViewModel.onIntent(intent)
                }
            }
        }
    )
}

@Composable
private fun HomeContent(
    modifier: Modifier = Modifier,
    state: HomeUiState,
    query: String,
    locations: List<Location>,
    onIntent: (HomeIntent) -> Unit
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
                onQueryChange = { onIntent(HomeIntent.OnSearch(it)) }
            )

            if (state.loading) {
                HomeSkeleton(modifier = Modifier.padding(top = 16.dp))
            } else {
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
}

@Preview(showBackground = true)
@Composable
private fun HomeContentPreview() {
    HomeContent(
        state = HomeUiState(),
        query = "",
        locations = listOf(),
        onIntent = {}
    )
}