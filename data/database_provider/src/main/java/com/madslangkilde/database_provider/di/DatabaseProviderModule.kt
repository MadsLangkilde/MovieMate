package com.madslangkilde.database_provider.di

import com.madslangkilde.database_provider.DatabaseProvider
import org.koin.dsl.module

val databaseProviderModule = module {
    single {
        DatabaseProvider()
    }
}