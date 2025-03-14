package com.madslangkilde.movielist.viewmodel

import com.madslangkilde.repository_movielist.entities.FavoriteMovieEntity

data class MovieListScreenUiState(
    val items: List<FavoriteMovieItem> = emptyList()
)

data class FavoriteMovieItem(
    val id: String,
    val movieId: String,
    val title: String,
    val genre: String,
    val year: String,
    val rating: String,
    val imdbID: String,
    val runtime: String,
    val poster: String
) {
    companion object {
        operator fun invoke(entity: FavoriteMovieEntity) = FavoriteMovieItem(
            id = entity.id,
            movieId = entity.movieId,
            title = entity.title,
            genre = entity.genre,
            year = entity.year,
            imdbID = entity.imdbID,
            runtime = entity.runtime,
            rating = entity.imdbRating,
            poster = entity.poster
        )

    }
}