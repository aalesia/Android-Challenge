package com.podium.technicalchallenge.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.podium.technicalchallenge.ui.dashboard.DashboardScreen
import com.podium.technicalchallenge.ui.details.DetailsScreen
import com.podium.technicalchallenge.ui.genre.GenreScreen

sealed class Screen(val route: String) {
    data object Dashboard : Screen("dashboard")
    data class Genre(val genre: String) : Screen("genre/{$genre}") {
        companion object {
            fun withGenre(genre: String) = "genre/$genre"
        }
    }
    data class Details(val movieId: Int) : Screen("details/{$movieId}") {
        companion object {
            fun withId(movieId: Int) = "details/$movieId"
        }
    }
}
@Composable
fun AppNavigator() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screen.Dashboard.route) {
        composable(Screen.Dashboard.route) { DashboardScreen(navController) }
        composable(
            Screen.Genre("genre").route,
            arguments = listOf(navArgument("genre") { type = NavType.StringType })
        ) { backStackEntry ->
            GenreScreen(navController, backStackEntry.arguments?.getString("genre") ?: "")
        }
        composable(
            "details/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            DetailsScreen(navController, backStackEntry.arguments?.getInt("movieId") ?: 0)
        }
    }
}