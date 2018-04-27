package de.rhm.github

import android.app.Application
import android.support.v7.app.AppCompatDelegate
import com.facebook.drawee.backends.pipeline.Fresco
import de.rhm.github.api.GitHubService
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit



class App: Application() {

    lateinit var apiService: GitHubService
    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        Fresco.initialize(this)
        apiService = Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(GitHubService::class.java)
    }
}