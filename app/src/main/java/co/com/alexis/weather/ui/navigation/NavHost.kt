package co.com.alexis.weather.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import co.com.alexis.weather.ui.detail.WeatherDetailScreen
import co.com.alexis.weather.ui.home.HomeScreen
import co.com.alexis.weather.ui.navigation.route.Route

@Composable
fun Navigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Route.Home.route
    ) {
        composable(Route.Home.route) {
            HomeScreen(
                navController = navController
            )
        }
        composable(
            route = Route.WeatherDetail.route,
            arguments = listOf(
                navArgument("location") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            WeatherDetailScreen(
                location = backStackEntry.arguments?.getString("location") ?: "",
                navController = navController
            )
        }
    }
}
