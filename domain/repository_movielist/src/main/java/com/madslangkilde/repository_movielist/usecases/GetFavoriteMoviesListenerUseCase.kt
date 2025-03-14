package com.madslangkilde.repository_movielist.usecases

import com.madslangkilde.repository_movielist.MovieListRepository
import com.madslangkilde.repository_movielist.entities.FavoriteMovieEntity
import kotlinx.coroutines.flow.Flow

class GetFavoriteMoviesListenerUseCase(private val movieListRepository: MovieListRepository) {
    suspend fun execute(): Flow<List<FavoriteMovieEntity>> {
        return movieListRepository.getFavoriteMoviesListener()
    }
}