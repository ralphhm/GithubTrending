package de.rhm.github

import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import de.rhm.github.api.Repository
import kotlinx.android.synthetic.main.item_repository.*

class RepositoryItem(val repo: Repository) : Item() {

    override fun bind(viewHolder: ViewHolder, position: Int) = with(viewHolder) {
        owner_image.setImageURI(repo.owner.avatarUrl)
        repo_name.text = repo.name
        repo_description.text = repo.description
        stargazers.text = "${repo.stars}"
        forks.text = "${repo.forkCount}"
    }

    override fun getLayout() = R.layout.item_repository
}