package co.com.alexis.weather.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import co.com.alexis.weather.domain.model.Location
import co.com.alexis.weather.ui.component.EmptyContentComponent
import co.com.alexis.weather.ui.component.ErrorDialog
import co.com.alexis.weather.ui.component.LocalErrorHandler
import co.com.alexis.weather.ui.component.SpacerComponent
import co.com.alexis.weather.ui.home.component.HomeSkeleton
import co.com.alexis.weather.ui.home.component.ItemLocation
import co.com.alexis.weather.ui.home.component.SearchComponent
import co.com.alexis.weather.ui.home.contract.HomeEffect
import co.com.alexis.weather.ui.home.contract.HomeIntent
import co.com.alexis.weather.ui.home.contract.HomeIntent.OnLocationSelected
import co.com.alexis.weather.ui.navigation.route.Route
import co.com.alexis.weather.ui.theme.WeatherGradients
import co.com.alexis.weather.ui.util.ResultState

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val state by homeViewModel.uiState.collectAsStateWithLifecycle()
    val query by homeViewModel.searchQuery.collectAsStateWithLifecycle()
    val errorHandler = LocalErrorHandler.current

    LaunchedEffect(Unit) {
        homeViewModel.effects.collect { effect ->
            when (effect) {
                is HomeEffect.ShowError -> {
                    errorHandler.showError(ErrorDialog(effect.message))
                }

                is HomeEffect.NavigateToDetail -> {
                    navController.navigate(Route.WeatherDetail.createRoute(effect.location))
                }
            }
        }
    }

    HomeContent(
        state = state,
        query = query,
        onIntent = { intent ->
            homeViewModel.onIntent(intent)
        }
    )
}

@Composable
private fun HomeContent(
    state: ResultState<List<Location>>,
    query: String,
    onIntent: (HomeIntent) -> Unit
) {
    Scaffold(
        modifier = Modifier
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(WeatherGradients.MainBackground)
                .padding(paddingValues),
        ) {
            SearchComponent(
                query = query,
                onQueryChange = { onIntent(HomeIntent.OnSearch(it)) }
            )

            when (state) {
                is ResultState.Loading -> {
                    HomeSkeleton(modifier = Modifier.padding(top = 16.dp))
                }

                is ResultState.Success -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        val locations = state.data
                        items(locations) { item ->
                            SpacerComponent(10)
                            ItemLocation(
                                location = item,
                                onItemSelected = { onIntent(OnLocationSelected(item.name)) }
                            )
                        }
                        if (locations.isEmpty()) {
                            item {
                                EmptyContentComponent()
                            }
                        }
                    }
                }

                else -> {
                    Unit
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeContentPreview() {
    HomeContent(
        state = ResultState.Idle,
        query = "",
        onIntent = {}
    )
}