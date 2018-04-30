package de.rhm.github

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import dagger.android.AndroidInjection
import de.rhm.github.api.GithubService
import de.rhm.github.api.SearchRepositories
import kotlinx.android.synthetic.main.activity_repo_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RepoListActivity : AppCompatActivity() {

    @Inject
    lateinit var apiService: GithubService

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
        apiService.getTrendingAndroidRepositories().enqueue(object: Callback<SearchRepositories> {
            override fun onResponse(call: Call<SearchRepositories>, response: Response<SearchRepositories>) {
                section.update(response.body()!!.repositories.map { RepositoryItem(it.owner.avatarUrl, it.fullName, it.description, it.stars, it.forkCount) })
            }

            override fun onFailure(call: Call<SearchRepositories>?, t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }
}
