package de.rhm.github

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import dagger.android.AndroidInjection
import de.rhm.github.RepoListUiModel.*
import de.rhm.github.di.TypedViewModelFactory
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_repo_list.*
import javax.inject.Inject

class RepoListActivity : AppCompatActivity() {

    @Inject
    lateinit var viewHolderFactory: TypedViewModelFactory<RepoListViewModel>
    private val section = Section()
    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_list)
        setSupportActionBar(toolbar)
        list.adapter = GroupAdapter<ViewHolder>().apply {
            add(section)
        }
        ViewModelProviders.of(this, viewHolderFactory).get(RepoListViewModel::class.java).run {
            uiState.subscribe { model -> updateUi(model) }.also { disposable.add(it) }
        }
    }

    private fun updateUi(model: RepoListUiModel) = when (model) {
        Loading -> Snackbar.make(content, "Loading", Snackbar.LENGTH_LONG).show()
        is Success -> section.update(model.repoList)
        is Failure -> Snackbar.make(content, model.message, Snackbar.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }
}
