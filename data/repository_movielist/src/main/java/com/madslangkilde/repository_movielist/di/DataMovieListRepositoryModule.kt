package com.madslangkilde.repository_movielist.di

import com.madslangkilde.repository_movielist.MovieListRepository
import com.madslangkilde.repository_movielist.MovieListRepositoryImpl
import com.madslangkilde.repository_movielist.listeners.MovieListListeners
import org.koin.dsl.module

val dataMovieListRepositoryModule = module {
    single { MovieListListeners() }
    single<MovieListRepository> { MovieListRepositoryImpl(get(), get()) }
}

