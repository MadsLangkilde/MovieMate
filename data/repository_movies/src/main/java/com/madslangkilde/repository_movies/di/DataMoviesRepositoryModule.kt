package com.madslangkilde.repository_movies.di

import com.madslangkilde.data.repository_movies.R
import com.madslangkilde.repository_movies.MoviesRepository
import com.madslangkilde.repository_movies.MoviesRepositoryImpl
import com.madslangkilde.repository_movies.network.OmdbServerConnector
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataMoviesRepositoryModule = module {
    single {
        OmdbServerConnector(
            androidContext().getString(R.string.omdb_api_key),
            androidContext().getString(R.string.omdb_base_url),
        )
    }
    factory<MoviesRepository> { MoviesRepositoryImpl(get(), get()) }
}

