package de.rhm.github

import android.arch.lifecycle.ViewModel
import de.rhm.github.api.GithubService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class RepoListViewModel @Inject constructor(apiService: GithubService): ViewModel() {
    val repositories: Single<List<RepositoryItem>> = apiService.getTrendingAndroidRepositories()
            .map { it.repositories.map { RepositoryItem(it.owner.avatarUrl, it.fullName, it.description, it.stars, it.forkCount) } }
            .cache()
            .observeOn(AndroidSchedulers.mainThread())
}