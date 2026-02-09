package co.com.alexis.weather.ui.home

import androidx.lifecycle.viewModelScope
import co.com.alexis.weather.domain.model.Location
import co.com.alexis.weather.domain.repository.IWeatherRepository
import co.com.alexis.weather.ui.home.contract.HomeEffect
import co.com.alexis.weather.ui.home.contract.HomeIntent
import co.com.alexis.weather.ui.util.BaseViewModel
import co.com.alexis.weather.ui.util.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val weatherRepository: IWeatherRepository
) : BaseViewModel<ResultState<List<Location>>, HomeEffect>(ResultState.Idle) {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    init {
        observer()
    }

    private fun observer() {
        _searchQuery
            .debounce(400)
            .distinctUntilChanged()
            .flatMapLatest { query -> searchLocations(query) }
            .onEach { state -> updateState { state } }
            .launchIn(scope = viewModelScope)
    }

    private fun searchLocations(query: String): Flow<ResultState<List<Location>>> =
        flow {
            if (query.isBlank()) {
                emit(ResultState.Idle)
            } else {
                weatherRepository.getLocations(query)
                    .onSuccess { locations ->
                        emit(ResultState.Success(locations))
                    }
                    .onFailure { exception ->
                        emit(ResultState.Idle)
                        emitEffect(HomeEffect.ShowError(exception.message ?: "Error"))
                    }
            }
        }.onStart {
            emit(ResultState.Loading)
        }

    fun onIntent(intent: HomeIntent) {
        viewModelScope.launch {
            when (intent) {
                is HomeIntent.OnSearch -> {
                    _searchQuery.value = intent.query
                }

                is HomeIntent.OnLocationSelected -> {
                    emitEffect(HomeEffect.NavigateToDetail(intent.location))
                }
            }
        }
    }

}