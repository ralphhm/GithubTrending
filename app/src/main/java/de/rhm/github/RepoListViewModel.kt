package de.rhm.github

import android.arch.lifecycle.ViewModel
import de.rhm.github.api.GithubService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class RepoListViewModel @Inject constructor(apiService: GithubService) : ViewModel() {
    private val publishSubject = PublishSubject.create<FetchRepoListAction>()
    val uiState: Observable<out RepoListUiModel> = publishSubject
            .startWith(FetchRepoListAction)
            .switchMap {
                apiService.getTrendingAndroidRepositories().toObservable()
                        .map<RepoListUiModel> { RepoListUiModel.Success(it.repositories.map { RepositoryItem(it.owner.avatarUrl, it.fullName, it.description, it.stars, it.forkCount) }) }
                        .startWith(RepoListUiModel.Loading)
                        .onErrorReturn { RepoListUiModel.Failure(it.localizedMessage) }
            }
            .replay(1)
            .autoConnect()
            .observeOn(AndroidSchedulers.mainThread())

}

object FetchRepoListAction
