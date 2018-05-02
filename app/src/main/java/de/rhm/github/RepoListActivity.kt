package de.rhm.github

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import dagger.android.AndroidInjection
import de.rhm.github.di.TypedViewModelFactory
import kotlinx.android.synthetic.main.activity_repo_list.*
import javax.inject.Inject

class RepoListActivity : AppCompatActivity() {

    @Inject
    lateinit var viewHolderFactory: TypedViewModelFactory<RepoListViewModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_list)
        setSupportActionBar(toolbar)
        val section = Section()
        GroupAdapter<ViewHolder>().apply {
                add(section)
        }.let {
            list.adapter = it
        }
        ViewModelProviders.of(this, viewHolderFactory).get(RepoListViewModel::class.java).repositories.subscribe { repoList -> section.update(repoList) }
    }
}
