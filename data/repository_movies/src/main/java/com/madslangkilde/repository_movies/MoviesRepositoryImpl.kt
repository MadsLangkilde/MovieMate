package com.madslangkilde.repository_movies

import com.madslangkilde.database_provider.DatabaseProvider
import com.madslangkilde.database_provider.models.FavoriteMovie
import com.madslangkilde.database_provider.models.Movie
import com.madslangkilde.repository_movies.entities.MovieEntity
import com.madslangkilde.repository_movies.mappers.toMovieResultsEntity
import com.madslangkilde.repository_movies.network.OmdbServerConnector
import io.realm.kotlin.ext.copyFromRealm

class MoviesRepositoryImpl(
    private val serverConnector: OmdbServerConnector,
    private val databaseProvider: DatabaseProvider
) : MoviesRepository {

    init {
        // TODO remove old cached values
    }

    override suspend fun search(query: String, ignoreCache: Boolean): List<MovieEntity> {
        val queryTrimmed = query.trim()
        if (queryTrimmed.isEmpty()) {
            return emptyList()
        }
        var results = databaseProvider.realm.query(
            Movie::class, "foundWithQuery == $0", queryTrimmed
        ).find().copyFromRealm()

        if (ignoreCache || results.isEmpty()) {
            val resultFomApi = serverConnector.search(queryTrimmed).searchResults ?: emptyList()
            val movies = ArrayList<Movie>()
            if (resultFomApi.isNotEmpty()) {
                databaseProvider.realm.writeBlocking {
                    resultFomApi.forEach {
                        val movie = Movie().apply {
                            title = it.title
                            genre = it.genre ?: ""
                            type = it.type ?: ""
                            imdbRating = it.imdbRating ?: ""
                            year = it.year
                            runtime = it.runtime ?: ""
                            imdbID = it.imdbID ?: ""
                            poster = it.poster ?: ""
                            foundWithQuery = queryTrimmed
                        }
                        movies.add(movie)
                        copyToRealm(movie)
                    }
                }
                results = movies
            }
        }

        val favorites = databaseProvider.realm.query(
            FavoriteMovie::class
        ).find().copyFromRealm()

        return results.toMovieResultsEntity(favorites)
    }
}