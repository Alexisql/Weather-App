package co.com.alexis.weather.ui.util

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * BaseViewModel.
 * @param S Representa el Estado de la UI
 * @param E Representa Efectos de un solo disparo (Navegación, Toasts, Error, etc.)
 */
abstract class BaseViewModel<S : Any, E : Any>(initialState: S) : ViewModel() {

    // Manejo de Estado (UI State)
    private val _uiState = MutableStateFlow(initialState)
    val uiState: StateFlow<S> = _uiState.asStateFlow()

    // Manejo de Efectos (Actions/Events)
    private val _effects = MutableSharedFlow<E>(
        replay = 0,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val effects: SharedFlow<E> = _effects.asSharedFlow()

    /**
     * Actualiza el estado de forma segura.
     */
    protected fun updateState(reducer: (S) -> S) {
        _uiState.update(reducer)
    }

    /**
     * Lanza un efecto que será procesado una sola vez por el colector.
     */
    protected fun emitEffect(effect: E) {
        _effects.tryEmit(effect)
    }

}