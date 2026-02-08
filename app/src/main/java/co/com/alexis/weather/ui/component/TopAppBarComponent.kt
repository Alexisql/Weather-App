package co.com.alexis.weather.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import co.com.alexis.weather.R
import co.com.alexis.weather.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TopAppBarComponent(
    @DrawableRes navigationIcon: Int? = null,
    title: String = "",
    containerColor: Color = Color.Transparent,
    onNavigationClick: () -> Unit = {}
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        CenterAlignedTopAppBar(
            title = {
                if (title.isNotBlank()) {
                    Text(
                        text = title,
                        style = Typography.titleLarge
                    )
                }
            },
            navigationIcon = {
                navigationIcon?.let {
                    IconButton(
                        onClick = { onNavigationClick() }
                    ) {
                        Icon(
                            painter = painterResource(id = it),
                            contentDescription = null
                        )
                    }
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = containerColor
            )
        )
    }
}

@Preview
@Composable
private fun TopBarComponentPreview() {
    TopAppBarComponent(
        navigationIcon = R.drawable.ic_arrow_left,
        title = "Detail",
        onNavigationClick = {}
    )
}