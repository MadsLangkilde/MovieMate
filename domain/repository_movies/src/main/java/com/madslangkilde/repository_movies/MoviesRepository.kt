package com.madslangkilde.repository_movies

import com.madslangkilde.repository_movies.entities.MovieEntity

interface MoviesRepository {
    suspend fun search(query: String, ignoreCache: Boolean = true) : List<MovieEntity>
}