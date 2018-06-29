package de.rhm.github.trending

import de.rhm.github.api.Repository

sealed class TrendingUiModel {
    object Loading : TrendingUiModel()
    class Success(val repoList: List<Repository>) : TrendingUiModel()
    class Failure(val cause: String?, val retryAction: () -> Unit) : TrendingUiModel()
}