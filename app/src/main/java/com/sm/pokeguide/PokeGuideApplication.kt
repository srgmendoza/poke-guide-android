package com.sm.pokeguide

import android.app.Application
import com.sm.pokeguide.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class PokeGuideApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@PokeGuideApplication)
            modules(mainModule)
        }
    }
}