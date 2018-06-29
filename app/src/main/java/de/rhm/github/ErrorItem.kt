package de.rhm.github

import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_error.*

class ErrorItem(val cause: String?, private val retryAction: () -> Unit) : Item() {

    override fun bind(viewHolder: ViewHolder, position: Int) = with(viewHolder) {
        info.text = itemView.resources.getString(R.string.error_loading_repos, cause)
        actionRetry.setOnClickListener { retryAction.invoke() }
    }

    override fun getLayout() = R.layout.item_error
}