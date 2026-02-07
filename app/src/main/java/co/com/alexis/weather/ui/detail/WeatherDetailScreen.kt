package co.com.alexis.weather.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import co.com.alexis.weather.R
import co.com.alexis.weather.domain.model.Weather
import co.com.alexis.weather.ui.component.ErrorDialog
import co.com.alexis.weather.ui.component.LocalErrorHandler
import co.com.alexis.weather.ui.component.TopAppBarComponent
import co.com.alexis.weather.ui.detail.component.WeatherDetailSkeleton
import co.com.alexis.weather.ui.detail.component.WeatherDetailSuccess
import co.com.alexis.weather.ui.detail.contract.WeatherDetailEffect
import co.com.alexis.weather.ui.detail.contract.WeatherDetailIntent
import co.com.alexis.weather.ui.util.ResultState

@Composable
fun WeatherDetailScreen(
    location: String,
    weatherDetailViewModel: WeatherDetailViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val state by weatherDetailViewModel.uiState.collectAsStateWithLifecycle()
    val errorHandler = LocalErrorHandler.current
    var titleTopAppBar by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(Unit) {
        weatherDetailViewModel.effects.collect { effect ->
            when (effect) {
                is WeatherDetailEffect.ShowError -> {
                    errorHandler.showError(ErrorDialog(effect.message))
                    navController.popBackStack()
                }

                WeatherDetailEffect.OnBack -> {
                    navController.popBackStack()
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        weatherDetailViewModel.getWeather(location, 3)
    }

    WeatherDetailContent(
        state = state,
        title = titleTopAppBar,
        onIntent = { intent ->
            when (intent) {
                is WeatherDetailIntent.OnChangeTitle -> {
                    titleTopAppBar = intent.title
                }

                else -> {
                    weatherDetailViewModel.onIntent(intent)
                }
            }
        }
    )
}

@Composable
private fun WeatherDetailContent(
    state: ResultState<Weather>,
    title: String = "",
    onIntent: (WeatherDetailIntent) -> Unit
) {
    Scaffold(
        modifier = Modifier,
        topBar = {
            TopAppBarComponent(
                navigationIcon = R.drawable.ic_arrow_left,
                title = title,
                onNavigationClick = {
                    onIntent(WeatherDetailIntent.OnBack)
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            when (state) {
                is ResultState.Loading -> {
                    WeatherDetailSkeleton()
                }

                is ResultState.Success -> {
                    val weather = state.data
                    onIntent(WeatherDetailIntent.OnChangeTitle(weather.location.name))
                    WeatherDetailSuccess(weather = weather)
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
private fun WeatherDetailContentPreview() {
    WeatherDetailContent(
        state = ResultState.Loading,
        onIntent = {}
    )
}