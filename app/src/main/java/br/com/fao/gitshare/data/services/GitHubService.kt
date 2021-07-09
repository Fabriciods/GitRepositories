package br.com.fao.gitshare.data.services

import br.com.fao.gitshare.data.model.Repo
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {
    @GET("users/{user}/repos")
     suspend fun listRepositories(@Path("user") user: String) : List<Repo>
}