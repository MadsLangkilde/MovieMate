package com.madslangkilde.search.viewmodel

import androidx.lifecycle.viewModelScope
import com.madslangkilde.repository_movielist.usecases.UpdateFavoriteMovieUseCase
import com.madslangkilde.repository_movies.usecases.SearchUseCase
import com.madslangkilde.ui_base.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

class SearchScreenViewModel(
    private val searchUseCase: SearchUseCase,
    private val updateFavoriteMovieUseCase: UpdateFavoriteMovieUseCase
) : BaseViewModel<SearchScreenUiState>(SearchScreenUiState()) {

    private var items: List<MovieItem> = emptyList()

    fun onIntent(searchScreenIntent: SearchScreenIntent) {
        viewModelScope.launch {
            when (searchScreenIntent) {
                is SearchScreenIntent.SearchMovie -> search(searchScreenIntent.query)
                is SearchScreenIntent.OnFavoriteChanged -> changedFavorite(searchScreenIntent.movieItem)
            }
        }
    }

    private suspend fun changedFavorite(movieItem: MovieItem) {
        val favorite = !movieItem.isFavorite
        val updatedItems = items.map { item ->
            if (item == movieItem) {
                item.copy(isFavorite = favorite)
            } else {
                item
            }
        }
        this.items = updatedItems
        updateUiState { it.copy(items = updatedItems) }
        updateFavoriteMovieUseCase.execute(movieItem.movieId, favorite)
    }

    private suspend fun search(query: String) {
        val results = searchUseCase.execute(query)
        val items = results.map {
            MovieItem(
                it.movieId,
                it.title,
                it.posterImageUrl,
                it.year,
                it.rating,
                it.isFavorite,
                it.imdbID
            )
        }
        this.items = items
        updateUiState { it.copy(items = items) }
    }
}