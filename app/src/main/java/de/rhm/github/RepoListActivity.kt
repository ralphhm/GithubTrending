package de.rhm.github

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import de.rhm.github.api.SearchRepositories
import kotlinx.android.synthetic.main.activity_repo_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepoListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_list)
        setSupportActionBar(toolbar)
        val section = Section()
        GroupAdapter<ViewHolder>().apply {
                add(section)
        }.let {
            list.adapter = it
        }
        App.instance.apiService.getTrendingAndroidRepositories().enqueue(object: Callback<SearchRepositories> {
            override fun onResponse(call: Call<SearchRepositories>, response: Response<SearchRepositories>) {
                section.update(response.body()!!.repositories.map { RepositoryItem(it.owner.avatarUrl, it.fullName, it.description, it.stars, it.forkCount) })
            }

            override fun onFailure(call: Call<SearchRepositories>?, t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }
}
