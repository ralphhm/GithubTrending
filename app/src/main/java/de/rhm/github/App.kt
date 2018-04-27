package de.rhm.github

import android.app.Application
import android.support.v7.app.AppCompatDelegate
import com.facebook.drawee.backends.pipeline.Fresco

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        Fresco.initialize(this);
    }
}