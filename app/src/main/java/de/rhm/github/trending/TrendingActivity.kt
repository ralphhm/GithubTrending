package de.rhm.github.trending

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import de.rhm.github.R
import de.rhm.github.repo.RepoDetailsActivity
import de.rhm.github.repo.SelectedRepo
import kotlinx.android.synthetic.main.activity_repo_list.*
import org.koin.android.architecture.ext.viewModel
import org.koin.android.ext.android.inject

class TrendingActivity : AppCompatActivity() {

    private val viewModel by viewModel<TrendingViewModel>()
    private val selectedRepository by inject<SelectedRepo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_list)
        setSupportActionBar(toolbar)
        TrendingView(viewModel).bind(content, this) { repo ->
            selectedRepository.value = repo
            startActivity(Intent(this@TrendingActivity, RepoDetailsActivity::class.java))
        }
    }
}
