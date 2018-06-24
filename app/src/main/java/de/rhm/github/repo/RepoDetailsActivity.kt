package de.rhm.github.repo

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import de.rhm.github.R
import de.rhm.github.api.Repository
import kotlinx.android.synthetic.main.activity_repo_details.*
import kotlinx.android.synthetic.main.content_repo_details.*
import org.koin.android.ext.android.inject

class RepoDetailsActivity : AppCompatActivity() {

    private val selectedRepo by inject<SelectedRepo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_details)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        selectedRepo.observe(this, Observer {
            bind(it!!)
        })
    }

    private fun bind(repo: Repository) = with(repo) {
        supportActionBar?.title = name
        owner_image.setImageURI(owner.avatarUrl)
        repo_description.text = description
        stargazers.text = "$stars"
        forks.text = "$forkCount"
    }

}
