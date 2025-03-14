package com.madslangkilde.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavHost(
    navController: NavHostController,
    navigator: Navigator,
    homeScreen: @Composable (Navigator) -> Unit,
    movieListScreen: @Composable (Navigator) -> Unit,
    searchScreen: @Composable (Navigator) -> Unit,
    editMovieNoteScreen: @Composable (Navigator, String) -> Unit,
    modifier: Modifier
) {

    NavHost(navController = navController, startDestination = NavigationRoute.START, modifier = modifier) {
        composable(NavigationRoute.START) {
            homeScreen(navigator)
        }
        composable(NavigationRoute.MOVIELIST) { backStackEntry ->
            movieListScreen(navigator)
        }
        composable(NavigationRoute.SEARCH) { backStackEntry ->
            searchScreen(navigator)
        }
        composable(NavigationRoute.EDIT_FAVORITE_MOVIE_NOTE) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("id") ?: ""
            editMovieNoteScreen(navigator, itemId)
        }
    }
}