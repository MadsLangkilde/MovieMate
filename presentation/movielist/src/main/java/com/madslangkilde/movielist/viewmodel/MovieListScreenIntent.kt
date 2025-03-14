package com.madslangkilde.movielist.viewmodel

sealed class MovieListScreenIntent {
    data class OnRemoveFavoriteClicked( val favoriteMovieItem: FavoriteMovieItem) : MovieListScreenIntent()
}