package de.rhm.github

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import de.rhm.github.RepoListUiModel.*
import de.rhm.github.repo.RepoDetailsActivity
import de.rhm.github.repo.SelectedRepo
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_repo_list.*
import org.koin.android.architecture.ext.viewModel
import org.koin.android.ext.android.inject

class RepoListActivity : AppCompatActivity() {

    private val viewModel by viewModel<RepoListViewModel>()
    private val selectedRepository by inject<SelectedRepo>()
    private val section = Section()
    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_list)
        setSupportActionBar(toolbar)
        list.adapter = GroupAdapter<ViewHolder>().apply {
            add(section)
            setOnItemClickListener { item, _ ->
                (item as? RepositoryItem)?.let {
                    selectedRepository.value = item.repo
                    startActivity(Intent(this@RepoListActivity, RepoDetailsActivity::class.java))
                }
            }
        }
        viewModel.uiState.subscribe { model -> updateUi(model) }.also { disposable.add(it) }
    }

    private fun updateUi(model: RepoListUiModel) = when (model) {
        Loading -> section.update(listOf(LoadingItem))
        is Success -> section.update(model.repoList.map { RepositoryItem(it) })
        is Failure -> section.update(listOf(ErrorItem(getString(R.string.error_loading_repos, model.cause), model.retryAction)))
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }
}
