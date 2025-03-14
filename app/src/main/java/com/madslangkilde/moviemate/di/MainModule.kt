package com.madslangkilde.moviemate.di

import com.madslangkilde.database_provider.di.databaseProviderModule
import com.madslangkilde.movielist.di.movieListFeatureModule
import com.madslangkilde.repository_movielist.di.dataMovieListRepositoryModule
import com.madslangkilde.repository_movielist.di.domainMovieListRepositoryModule
import com.madslangkilde.repository_movies.di.dataMoviesRepositoryModule
import com.madslangkilde.repository_movies.di.domainMoviesRepository
import com.madslangkilde.search.di.searchFeatureModule
import com.madslangkilde.start.di.startFeatureModule

val mainModules = listOf(
    startFeatureModule,
    movieListFeatureModule,
    searchFeatureModule,
    domainMoviesRepository,
    databaseProviderModule,
    dataMoviesRepositoryModule,
    dataMovieListRepositoryModule,
    domainMovieListRepositoryModule
)