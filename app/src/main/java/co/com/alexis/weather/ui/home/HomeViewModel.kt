package co.com.alexis.weather.ui.home

import androidx.lifecycle.viewModelScope
import co.com.alexis.weather.domain.model.Location
import co.com.alexis.weather.domain.repository.IWeatherRepository
import co.com.alexis.weather.ui.home.contract.HomeEffect
import co.com.alexis.weather.ui.home.contract.HomeIntent
import co.com.alexis.weather.ui.home.contract.HomeUiState
import co.com.alexis.weather.ui.util.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val weatherRepository: IWeatherRepository
) : BaseViewModel<HomeUiState, HomeEffect>(HomeUiState()) {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    val locations: StateFlow<List<Location>> =
        _searchQuery
            .debounce(400)
            .distinctUntilChanged()
            .flatMapLatest { query ->
                flow {
                    updateState { it.copy(loading = true) }
                    if (query.isBlank()) {
                        emit(emptyList())
                    } else {
                        emit(weatherRepository.getLocations(query))
                    }
                    updateState { state -> state.copy(loading = false) }
                }.catch { exception ->
                    if (exception !is CancellationException) {
                        updateState { state -> state.copy(loading = false) }
                        emit(emptyList())
                        emitEffect(HomeEffect.ShowError(exception.message ?: "Error"))
                    }
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    private fun searchLocation(location: String) {
        _searchQuery.value = location
    }

    fun onIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.OnSearch -> {
                searchLocation(intent.query)
            }
        }
    }

}