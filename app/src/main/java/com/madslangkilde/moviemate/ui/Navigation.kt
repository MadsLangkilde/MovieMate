package com.madslangkilde.moviemate.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.madslangkilde.edit_favorite_movie.ui.EditFavoriteMovieScreen
import com.madslangkilde.movielist.ui.MovieListScreen
import com.madslangkilde.navigation.AppNavHost
import com.madslangkilde.navigation.Navigator
import com.madslangkilde.search.ui.SearchScreen
import com.madslangkilde.start.ui.StartScreen

@Composable
fun Navigation(
    navController: NavHostController,
    navigator: Navigator,
    modifier: Modifier = Modifier
) {
    AppNavHost(
        navController = navController,
        navigator = navigator,
        homeScreen = { StartScreen(it) },
        movieListScreen = { MovieListScreen(it) },
        searchScreen = { SearchScreen(it) },
        editMovieNoteScreen = { nav, id -> EditFavoriteMovieScreen(nav, id) },
        modifier
    )
}
