package com.madslangkilde.movielist.viewmodel

import androidx.lifecycle.viewModelScope
import com.madslangkilde.repository_movielist.usecases.GetFavoriteMoviesListenerUseCase
import com.madslangkilde.repository_movielist.usecases.UpdateFavoriteMovieUseCase
import com.madslangkilde.ui_base.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

class MovieListScreenViewModel(
    private val updateFavoriteMovieUseCase: UpdateFavoriteMovieUseCase,
    private val getFavoriteMoviesListenerUseCase: GetFavoriteMoviesListenerUseCase
) : BaseViewModel<MovieListScreenUiState>(MovieListScreenUiState()) {

    private lateinit var items: List<FavoriteMovieItem>

    init {
        listenForFavoriteMovies()
    }


    fun onIntent(intent: MovieListScreenIntent) {
        viewModelScope.launch {
            when (intent) {
                is MovieListScreenIntent.OnRemoveFavoriteClicked -> removeFavorite(intent.favoriteMovieItem)
            }
        }
    }

    private fun listenForFavoriteMovies() {
        viewModelScope.launch {
            getFavoriteMoviesListenerUseCase.execute().collect { entities ->
                items = entities.map { FavoriteMovieItem(it) }
                updateUiState {
                    it.copy(items = items)
                }
            }
        }
    }

    private suspend fun removeFavorite(movieItem: FavoriteMovieItem) {
        val updatedItems = items.filter { it.movieId != movieItem.movieId }
        this.items = updatedItems
        updateUiState { it.copy(items = updatedItems) }
        updateFavoriteMovieUseCase.execute(movieItem.movieId, false)
    }
}