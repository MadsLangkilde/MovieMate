package com.madslangkilde.repository_movielist.listeners

import com.madslangkilde.repository_movielist.entities.FavoriteMovieEntity
import kotlinx.coroutines.flow.MutableStateFlow

class MovieListListeners {
    internal val favoriteMoviesListener = MutableStateFlow<List<FavoriteMovieEntity>>(emptyList())
}