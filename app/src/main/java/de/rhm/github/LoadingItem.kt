package de.rhm.github

import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder

object LoadingItem : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) = Unit
    override fun getLayout() = R.layout.item_loading
}