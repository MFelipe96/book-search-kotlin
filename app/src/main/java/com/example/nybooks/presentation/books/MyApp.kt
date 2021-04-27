package com.example.nybooks.presentation.books

import android.app.Application
import com.example.nybooks.di.bookModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyApp)
            modules(bookModule)
        }
    }
}