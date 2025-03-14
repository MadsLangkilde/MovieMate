package com.madslangkilde.start.di

import com.madslangkilde.start.viewmodel.StartScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val startFeatureModule = module {
    viewModel { StartScreenViewModel() }
}