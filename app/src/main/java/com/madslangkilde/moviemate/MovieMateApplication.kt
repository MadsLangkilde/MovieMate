package com.madslangkilde.moviemate

import android.app.Application
import com.madslangkilde.moviemate.di.mainModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class MovieMateApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MovieMateApplication)
            androidLogger(Level.ERROR)
            modules(mainModules)
        }
    }
}