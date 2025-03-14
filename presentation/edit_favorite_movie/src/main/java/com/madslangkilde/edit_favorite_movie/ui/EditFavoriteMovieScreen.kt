package com.madslangkilde.edit_favorite_movie.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.madslangkilde.navigation.Navigator

@Composable
fun EditFavoriteMovieScreen(navigator: Navigator, movieId: String?) {
    Text(text = "Edit favorite move with id: $movieId")
}