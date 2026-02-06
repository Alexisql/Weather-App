package co.com.alexis.weather.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

val LocalErrorHandler = compositionLocalOf<ErrorHandler> {
    error("Error handler not provided")
}

data class ErrorDialog(
    val title: String? = null,
    val message: String? = null
)

class ErrorHandler {
    var error by mutableStateOf<ErrorDialog?>(null)

    fun showError(error: ErrorDialog) {
        this.error = error
    }

    fun dismiss() {
        error = null
    }
}

@Composable
fun ErrorHandlerContext(
    content: @Composable () -> Unit
) {
    val errorHandler = remember { ErrorHandler() }

    CompositionLocalProvider(LocalErrorHandler provides errorHandler) {
        content()
        errorHandler.error?.let {
            ErrorDialogComponent(
                error = it,
                onDismiss = errorHandler::dismiss
            )
        }
    }
}