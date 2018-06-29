package de.rhm.github.trending

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import android.view.View
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import de.rhm.github.api.Repository
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_repo_list.view.*

class TrendingView(viewModel: TrendingViewModel): LifecycleObserver {

    private val section = Section()
    private val disposable = CompositeDisposable()

    init {
        viewModel.uiState.subscribe { model -> updateUi(model) }.also { disposable.add(it) }
    }

    fun bind(view: View, lifecycleOwner: LifecycleOwner, navigation: (Repository) -> Unit) {
        lifecycleOwner.lifecycle.addObserver(this)
        view.list.adapter = GroupAdapter<ViewHolder>().apply {
            add(section)
            setOnItemClickListener { item, _ ->
                (item as? RepositoryItem)?.let {
                    navigation.invoke(item.repo)
                }
            }
        }
    }

    private fun updateUi(model: TrendingUiModel) = when (model) {
        TrendingUiModel.Loading -> section.update(listOf(LoadingItem))
        is TrendingUiModel.Success -> section.update(model.repoList.map { RepositoryItem(it) })
        is TrendingUiModel.Failure -> section.update(listOf(ErrorItem(model.cause, model.retryAction)))
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onEvent(owner: LifecycleOwner) {
        disposable.dispose()
    }
}