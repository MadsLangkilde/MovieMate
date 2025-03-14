package com.madslangkilde.repository_movies.di

import com.madslangkilde.repository_movies.usecases.SearchUseCase
import org.koin.dsl.module

val domainMoviesRepository = module {
    factory { SearchUseCase(get()) }
}