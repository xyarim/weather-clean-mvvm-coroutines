package com.xyarim.weather.nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.xyarim.weather.weather.current.WeatherMainScreen
import com.xyarim.weather.weather.day.DayScreen
import com.xyarim.weather.weather.day.DayScreenViewModel
import com.xyarim.weather.weather.day.DayScreenViewModel.DayScreenViewModelFactory


object AppDestinations {
    const val WEATHER = "weather"
    const val WEATHER_DATE = "/{date}"
    const val DAY = "day"
}

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    startDestinations: String = AppDestinations.WEATHER
) {

    val actions = remember(navController) {
        AppActions(navController)
    }

    NavHost(
        navController = navController,
        startDestination = startDestinations
    ) {
        composable(AppDestinations.WEATHER) {
            WeatherMainScreen(
                openDayForecast = actions.navigateToDay,
            )
        }
        composable(
            AppDestinations.DAY + AppDestinations.WEATHER_DATE, arguments = listOf(
                navArgument("date") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val viewModel =
                hiltViewModel<DayScreenViewModel, DayScreenViewModelFactory> { dayScreenViewModelFactory ->
                    dayScreenViewModelFactory.create(
                        backStackEntry.arguments?.getString("date") ?: ""
                    )
                }
            DayScreen(
                viewModel = viewModel,
                navigateUp = actions.navigateUp
            )
        }
    }
}

class AppActions(
    navController: NavHostController
) {
    val navigateToDay: (date: String) -> Unit = {
        navController.navigate(AppDestinations.DAY + "/${it}")
    }
    val navigateUp: () -> Unit = {
        navController.navigateUp()
    }
}