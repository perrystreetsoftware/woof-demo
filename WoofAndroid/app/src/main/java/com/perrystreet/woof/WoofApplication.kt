package com.perrystreet.woof

import android.app.Application
import com.perrystreet.woof.di.WoofKoinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WoofApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WoofApplication)
            modules(WoofKoinModules.all)
        }
    }
}
