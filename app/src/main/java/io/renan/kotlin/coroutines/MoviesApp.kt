package io.renan.kotlin.coroutines

import android.app.Application
import io.renan.kotlin.coroutines.di.appModule
import io.renan.kotlin.coroutines.di.networkingModule
import io.renan.kotlin.coroutines.di.presenterModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MoviesApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MoviesApp)
            androidLogger()
            modules(listOf(appModule(), networkingModule(), presenterModule()))
        }

    }


}