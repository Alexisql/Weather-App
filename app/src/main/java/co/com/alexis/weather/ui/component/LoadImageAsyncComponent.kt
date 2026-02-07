package co.com.alexis.weather.ui.component

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import co.com.alexis.weather.R
import coil3.compose.AsyncImage

@Composable
fun LoadImageAsyncComponent(url: String) {
    AsyncImage(
        model = url,
        contentDescription = null,
        modifier = Modifier.size(100.dp),
        placeholder = painterResource(R.drawable.ic_placeholder),
        error = painterResource(R.drawable.ic_not_found),
        contentScale = ContentScale.Fit
    )
}