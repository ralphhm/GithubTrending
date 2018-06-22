package de.rhm.github

import android.app.Application
import android.support.v7.app.AppCompatDelegate
import com.facebook.drawee.backends.pipeline.Fresco
import org.koin.android.ext.android.startKoin

class App : Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin(listOf(AppModule))
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        Fresco.initialize(this)
    }
}