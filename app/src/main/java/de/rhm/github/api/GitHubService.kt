package de.rhm.github.api

import retrofit2.Call
import retrofit2.http.GET

interface GitHubService {
    @GET("search/repositories?q=topic:android&sort=stars&order=desc")
    fun getTrendingAndroidRepositories(): Call<SearchRepositories>
}