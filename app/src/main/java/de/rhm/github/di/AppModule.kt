package de.rhm.github.di

import android.app.Application
import dagger.Module
import dagger.Provides
import de.rhm.github.api.GithubService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application) = application

    @Provides
    @Singleton
    fun provideGithubService(): GithubService = Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(GithubService::class.java)

}