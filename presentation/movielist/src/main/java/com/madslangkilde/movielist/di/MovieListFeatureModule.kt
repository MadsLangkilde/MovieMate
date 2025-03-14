package com.madslangkilde.movielist.di

import com.madslangkilde.movielist.viewmodel.MovieListScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val movieListFeatureModule = module {
    viewModel { MovieListScreenViewModel(get(), get()) }
}