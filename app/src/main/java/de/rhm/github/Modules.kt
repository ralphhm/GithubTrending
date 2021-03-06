package de.rhm.github

import de.rhm.github.api.GithubService
import de.rhm.github.repo.SelectedRepo
import de.rhm.github.trending.TrendingViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.applicationContext
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val AppModule = applicationContext {

    bean { AndroidSchedulers.mainThread() }

    bean {
        Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build().create(GithubService::class.java)
    }

    viewModel { TrendingViewModel(get(), get()) }

    bean { SelectedRepo() }

}