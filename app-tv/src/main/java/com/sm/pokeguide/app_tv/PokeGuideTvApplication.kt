package com.sm.pokeguide.app_tv

import android.app.Application
import com.sm.pokeguide.app_tv.di.mainTvModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PokeGuideTvApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@PokeGuideTvApplication)
            modules(mainTvModule)
        }
    }
}
