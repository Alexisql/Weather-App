package co.com.alexis.weather.ui.detail

import androidx.lifecycle.viewModelScope
import co.com.alexis.weather.domain.repository.IWeatherRepository
import co.com.alexis.weather.ui.detail.contract.WeatherDetailEffect
import co.com.alexis.weather.ui.detail.contract.WeatherDetailIntent
import co.com.alexis.weather.ui.detail.contract.WeatherDetailUiState
import co.com.alexis.weather.ui.util.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherDetailViewModel @Inject constructor(
    private val weatherRepository: IWeatherRepository
) : BaseViewModel<WeatherDetailUiState, WeatherDetailEffect>(WeatherDetailUiState.Idle) {

    fun getWeather(location: String, numberDays: Int) {
        updateState { WeatherDetailUiState.Loading }
        viewModelScope.launch {
            val response = weatherRepository.getWeather(location, numberDays)
            response
                .onSuccess { weather ->
                    updateState { WeatherDetailUiState.Success(weather) }
                }
                .onFailure {
                    updateState { WeatherDetailUiState.Idle }
                    emitEffect(WeatherDetailEffect.ShowError(it.message ?: "Error"))
                }
        }
    }

    fun onIntent(intent: WeatherDetailIntent) {
        when (intent) {
            WeatherDetailIntent.OnBack -> {
                emitEffect(WeatherDetailEffect.OnBack)
            }

            else -> {
                Unit
            }
        }
    }

}