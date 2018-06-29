package de.rhm.github.trending

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import de.rhm.github.api.GithubService
import de.rhm.github.api.SearchRepositories
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class RepoListViewModelTest {

    val observer = TestObserver.create<RepoListUiModel>()
    val scheduler = Schedulers.trampoline()

    @Test
    fun `emits loading state`() {
        val service = mock<GithubService> {
            on { getTrendingAndroidRepositories() } doReturn (Single.never<SearchRepositories>())
        }
        RepoListViewModel(service, scheduler).uiState.subscribe(observer)
        assertThat(observer.values().last(), instanceOf(RepoListUiModel.Loading::class.java))
    }

    @Test
    fun `emits failure state on error`() {
        val service = mock<GithubService> {
            on { getTrendingAndroidRepositories() } doReturn (Single.error<SearchRepositories>(Exception()))
        }
        RepoListViewModel(service, scheduler).uiState.subscribe(observer)
        assertThat(observer.values().last(), instanceOf(RepoListUiModel.Failure::class.java))
    }

    @Test
    fun `emits result state on success`() {
        val service = mock<GithubService> {
            on { getTrendingAndroidRepositories() } doReturn (Single.just(SearchRepositories()))
        }
        RepoListViewModel(service, scheduler).uiState.subscribe(observer)
        assertThat(observer.values().last(), instanceOf(RepoListUiModel.Success::class.java))
    }

    @Test
    fun `emits cached state after resubscribing`() {
        val service = mock<GithubService> {
            on { getTrendingAndroidRepositories() } doReturn (Single.just(SearchRepositories()))
        }
        RepoListViewModel(service, scheduler).uiState.apply {
            subscribe()
            subscribe(observer)
        }
        assertThat(observer.values().last(), instanceOf(RepoListUiModel.Success::class.java))
    }
}