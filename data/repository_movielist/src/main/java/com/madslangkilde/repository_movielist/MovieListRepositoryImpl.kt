package com.madslangkilde.repository_movielist

import com.madslangkilde.database_provider.DatabaseProvider
import com.madslangkilde.database_provider.models.FavoriteMovie
import com.madslangkilde.database_provider.models.Movie
import com.madslangkilde.repository_movielist.entities.FavoriteMovieEntity
import com.madslangkilde.repository_movielist.listeners.MovieListListeners
import com.madslangkilde.repository_movielist.mappers.toFavoriteMovieEntities
import io.realm.kotlin.ext.copyFromRealm
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.UUID

class MovieListRepositoryImpl(
    private val databaseProvider: DatabaseProvider,
    private val listeners: MovieListListeners
) : MovieListRepository {

    init {
        CoroutineScope(Dispatchers.IO).launch {
            listeners.favoriteMoviesListener.emit(getListOfFavorites())
        }
    }

    override suspend fun updateFavoriteMovie(movieId: String, favorite: Boolean) {
        val foundMovie = databaseProvider.realm.query(
            Movie::class, "id == $0", movieId
        ).first().find()?.copyFromRealm()

        val foundFavoriteMovie = databaseProvider.realm.query(
            FavoriteMovie::class, "movieId == $0", movieId
        ).first().find()

        if (foundMovie != null) {
            if (!favorite) {
                databaseProvider.realm.writeBlocking {
                    val entry = query(FavoriteMovie::class, "movieId == $0", movieId).first().find()
                    if (entry != null) {
                        delete(entry)
                    }
                }
            } else {
                val favoriteMovie = FavoriteMovie().apply {
                    this.movieId = foundFavoriteMovie?.id ?: UUID.randomUUID().toString()
                    this.movieId = foundMovie.id
                    this.title = foundMovie.title
                    this.genre = foundMovie.genre
                    this.year = foundMovie.year
                    this.imdbID = foundMovie.imdbID
                    this.imdbRating = foundMovie.imdbRating
                    this.runtime = foundMovie.runtime
                    this.type = foundMovie.type
                    this.poster = foundMovie.poster
                }
                databaseProvider.realm.writeBlocking {
                    this.copyToRealm(favoriteMovie)
                }
            }

            listeners.favoriteMoviesListener.emit(getListOfFavorites())
        }
    }

    override suspend fun getListOfFavorites(): List<FavoriteMovieEntity> {
        val favorites = databaseProvider.realm.query(
            FavoriteMovie::class
        ).find().copyFromRealm()
        return favorites.toFavoriteMovieEntities()
    }

    override suspend fun getFavoriteMoviesListener(): Flow<List<FavoriteMovieEntity>> {
        return listeners.favoriteMoviesListener
    }
}