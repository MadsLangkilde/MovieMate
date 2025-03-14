package com.madslangkilde.repository_movielist.di

import com.madslangkilde.repository_movielist.usecases.GetFavoriteMoviesListenerUseCase
import com.madslangkilde.repository_movielist.usecases.UpdateFavoriteMovieUseCase
import org.koin.dsl.module

val domainMovieListRepositoryModule = module {
    factory { GetFavoriteMoviesListenerUseCase(get()) }
    factory { UpdateFavoriteMovieUseCase(get()) }
}