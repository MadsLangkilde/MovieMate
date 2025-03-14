package com.madslangkilde.search.viewmodel

data class SearchScreenUiState(
    val items: List<MovieItem> = emptyList()
)

data class MovieItem(
    val movieId: String,
    val title: String,
    val imageUrl: String,
    val year: String,
    val rating: String,
    val isFavorite: Boolean,
    val imdbID: String
)