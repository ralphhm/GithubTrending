package de.rhm.github

import de.rhm.github.api.Repository

sealed class RepoListUiModel {
    object Loading : RepoListUiModel()
    class Success(val repoList: List<Repository>) : RepoListUiModel()
    class Failure(val cause: String?, val retryAction: () -> Unit) : RepoListUiModel()
}