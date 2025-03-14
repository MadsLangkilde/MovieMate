package com.madslangkilde.search.di

import com.madslangkilde.search.viewmodel.SearchScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val searchFeatureModule = module {
    viewModel { SearchScreenViewModel(get(), get()) }
}