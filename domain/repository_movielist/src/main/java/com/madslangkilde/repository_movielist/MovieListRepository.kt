package com.madslangkilde.repository_movielist

import com.madslangkilde.repository_movielist.entities.FavoriteMovieEntity
import kotlinx.coroutines.flow.Flow


interface MovieListRepository {
    suspend fun getListOfFavorites(): List<FavoriteMovieEntity>
    suspend fun getFavoriteMoviesListener(): Flow<List<FavoriteMovieEntity>>
    suspend fun updateFavoriteMovie(movieId: String, favorite: Boolean)
}