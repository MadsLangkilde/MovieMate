package com.madslangkilde.repository_movielist.usecases

import com.madslangkilde.repository_movielist.MovieListRepository

class UpdateFavoriteMovieUseCase(private val movieListRepository: MovieListRepository) {
    suspend fun execute(movieId: String, isFavorite: Boolean) {
        movieListRepository.updateFavoriteMovie(movieId, isFavorite)
    }
}