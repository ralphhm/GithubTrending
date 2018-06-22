package de.rhm.github

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import de.rhm.github.RepoListUiModel.*
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_repo_list.*
import org.koin.android.architecture.ext.viewModel

class RepoListActivity : AppCompatActivity() {

    private val viewModel by viewModel<RepoListViewModel>()
    private val section = Section()
    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_list)
        setSupportActionBar(toolbar)
        list.adapter = GroupAdapter<ViewHolder>().apply {
            add(section)
        }
        viewModel.uiState.subscribe { model -> updateUi(model) }.also { disposable.add(it) }
    }

    private fun updateUi(model: RepoListUiModel) = when (model) {
        Loading -> section.update(listOf(LoadingItem))
        is Success -> section.update(model.repoList)
        is Failure -> section.update(listOf(ErrorItem(getString(R.string.error_loading_repos, model.cause))))
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }
}
