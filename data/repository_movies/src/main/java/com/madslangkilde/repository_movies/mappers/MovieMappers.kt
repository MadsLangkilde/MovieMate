package com.madslangkilde.repository_movies.mappers

import com.madslangkilde.database_provider.models.FavoriteMovie
import com.madslangkilde.database_provider.models.Movie
import com.madslangkilde.repository_movies.entities.MovieEntity

fun List<Movie>.toMovieResultsEntity(favoriteMovies: List<FavoriteMovie>): List<MovieEntity> {
    val favoriteMovieIds = ArrayList<String>(favoriteMovies.map { it.movieId })
    return this.map {
        MovieEntity(
            it.id,
            it.title,
            it.year,
            it.imdbRating,
            it.imdbID,
            it.type,
            it.poster,
            favoriteMovieIds.contains(it.id)
        )
    }
}