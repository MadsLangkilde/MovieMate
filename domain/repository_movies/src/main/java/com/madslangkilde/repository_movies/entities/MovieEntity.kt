package com.madslangkilde.repository_movies.entities

data class MovieEntity(
    val movieId: String,
    val title: String,
    val year: String,
    val rating: String,
    val imdbID: String,
    val type: String,
    val posterImageUrl: String,
    val isFavorite: Boolean
)