package com.madslangkilde.search.viewmodel

sealed interface SearchScreenIntent {
    data class SearchMovie(val query: String) : SearchScreenIntent
    data class OnFavoriteChanged(val movieItem: MovieItem) : SearchScreenIntent
}