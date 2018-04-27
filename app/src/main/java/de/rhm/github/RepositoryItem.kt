package de.rhm.github

import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_repository.*

class RepositoryItem(private val ownerImageUrl: String, private val repoName: String, private val repoDescription: String, private val stargazerCount: Int, private val forkCount:Int): Item() {

    override fun bind(viewHolder: ViewHolder, position: Int) = with(viewHolder) {
        owner_image.setImageURI(ownerImageUrl)
        repo_name.text = repoName
        repo_description.text = repoDescription
        stargazers.text = "$stargazerCount"
        forks.text = "$forkCount"
    }

    override fun getLayout() = R.layout.item_repository
}