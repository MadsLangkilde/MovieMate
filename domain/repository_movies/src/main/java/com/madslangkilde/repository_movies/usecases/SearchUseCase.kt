package com.madslangkilde.repository_movies.usecases

import com.madslangkilde.repository_movies.MoviesRepository
import com.madslangkilde.repository_movies.entities.MovieEntity

class SearchUseCase(private val moviesRepository: MoviesRepository) {
    suspend fun execute(query: String, ignoreCache: Boolean = false) : List<MovieEntity> = moviesRepository.search(query, ignoreCache)
}