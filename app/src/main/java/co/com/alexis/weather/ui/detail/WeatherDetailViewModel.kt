package co.com.alexis.weather.ui.detail

import androidx.lifecycle.viewModelScope
import co.com.alexis.weather.domain.model.Weather
import co.com.alexis.weather.domain.repository.IWeatherRepository
import co.com.alexis.weather.ui.detail.contract.WeatherDetailEffect
import co.com.alexis.weather.ui.detail.contract.WeatherDetailIntent
import co.com.alexis.weather.ui.util.BaseViewModel
import co.com.alexis.weather.ui.util.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherDetailViewModel @Inject constructor(
    private val weatherRepository: IWeatherRepository
) : BaseViewModel<ResultState<Weather>, WeatherDetailEffect>(ResultState.Idle) {

    fun getWeather(location: String, numberDays: Int) {
        updateState { ResultState.Loading }
        viewModelScope.launch {
            val response = weatherRepository.getWeather(location, numberDays)
            response
                .onSuccess { weather ->
                    updateState { ResultState.Success(weather) }
                }
                .onFailure {
                    updateState { ResultState.Idle }
                    emitEffect(WeatherDetailEffect.ShowError(it.message ?: "Error"))
                }
        }
    }

    fun onIntent(intent: WeatherDetailIntent) {
        viewModelScope.launch {
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

}