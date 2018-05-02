package de.rhm.github.api

import io.reactivex.Single
import retrofit2.http.GET

interface GithubService {
    @GET("search/repositories?q=topic:android&sort=stars&order=desc")
    fun getTrendingAndroidRepositories(): Single<SearchRepositories>
}