package co.com.alexis.weather.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SpacerComponent(size: Int) {
    Spacer(modifier = Modifier.size(size.dp))
}