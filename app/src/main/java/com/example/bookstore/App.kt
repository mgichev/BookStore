package com.example.bookstore

import android.app.Application
import com.example.bookstore.di.dataModule
import com.example.bookstore.di.dbModule
import com.example.bookstore.di.domainModule
import com.example.bookstore.di.networkModule
import com.example.bookstore.di.viewModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(dataModule, domainModule, viewModule, networkModule, dbModule)
        }
    }
}