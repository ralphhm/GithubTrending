package de.rhm.github

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.activity_repo_list.*

class RepoListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_list)
        setSupportActionBar(toolbar)
        GroupAdapter<ViewHolder>().apply {
            Section().apply {
                add(RepositoryItem("name", "description", 12345, 23))
            }.let {
                add(it)
            }
        }.let {
            list.adapter = it
        }
    }
}
