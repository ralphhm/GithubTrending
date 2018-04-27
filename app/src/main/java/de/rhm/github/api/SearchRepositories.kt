package de.rhm.github.api

import com.google.gson.annotations.SerializedName

class SearchRepositories(
        @SerializedName("items")
        val repositories: List<Repository>
) {
}