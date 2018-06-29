package de.rhm.github.trending

import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import de.rhm.github.R
import de.rhm.github.api.Repository
import kotlinx.android.synthetic.main.item_error.*
import kotlinx.android.synthetic.main.item_repository.*

object LoadingItem : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) = Unit
    override fun getLayout() = R.layout.item_loading
}

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

class ErrorItem(val cause: String?, private val retryAction: () -> Unit) : Item() {

    override fun bind(viewHolder: ViewHolder, position: Int) = with(viewHolder) {
        info.text = itemView.resources.getString(R.string.error_loading_repos, cause)
        actionRetry.setOnClickListener { retryAction.invoke() }
    }

    override fun getLayout() = R.layout.item_error
}