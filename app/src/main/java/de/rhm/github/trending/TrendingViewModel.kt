package de.rhm.github.trending

import android.arch.lifecycle.ViewModel
import de.rhm.github.api.GithubService
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.subjects.PublishSubject

class TrendingViewModel(apiService: GithubService, scheduler: Scheduler) : ViewModel() {
    private val publishSubject = PublishSubject.create<FetchTrendingReposAction>()
    val uiState: Observable<out TrendingUiModel> = publishSubject
            //fetch on ViewModel creation
            .startWith(FetchTrendingReposAction)
            .switchMap {
                apiService.getTrendingAndroidRepositories().toObservable()
                        .map<TrendingUiModel> { TrendingUiModel.Success(it.repositories) }
                        .startWith(TrendingUiModel.Loading)
                        .onErrorReturn { TrendingUiModel.Failure(it.localizedMessage) { publishSubject.onNext(FetchTrendingReposAction) } }
            }
            //cache latest emitted state
            .replay(1)
            .autoConnect(0)
            .observeOn(scheduler)

    private object FetchTrendingReposAction

}

