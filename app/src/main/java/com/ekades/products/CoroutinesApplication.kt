package com.ekades.products

import android.app.Application
import com.ekades.products.di.modules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CoroutinesApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CoroutinesApplication)
            androidLogger()
            modules(modules)
        }
    }
}