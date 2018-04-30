package de.rhm.github

import DaggerAppComponent
import android.app.Activity
import android.app.Application
import android.support.v7.app.AppCompatDelegate
import com.facebook.drawee.backends.pipeline.Fresco
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class App : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        Fresco.initialize(this)
    }

    override fun activityInjector() = dispatchingAndroidInjector
}