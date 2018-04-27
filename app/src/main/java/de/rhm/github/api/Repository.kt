package de.rhm.github.api

import com.google.gson.annotations.SerializedName

class Repository(
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("full_name")
        val fullName: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("stargazers_count")
        val stars: Int,
        @SerializedName("owner")
        val owner: Owner,
        @SerializedName("forks_count")
        val forkCount: Int
) {
    class Owner(
            @SerializedName("avatar_url")
            val avatarUrl: String?
    )
}