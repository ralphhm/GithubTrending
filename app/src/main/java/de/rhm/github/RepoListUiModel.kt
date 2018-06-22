package de.rhm.github

sealed class RepoListUiModel {
    object Loading : RepoListUiModel()
    class Success(val repoList: List<RepositoryItem>) : RepoListUiModel()
    class Failure(val cause: String) : RepoListUiModel()
}