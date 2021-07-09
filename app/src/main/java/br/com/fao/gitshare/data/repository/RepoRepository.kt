package br.com.fao.gitshare.data.repository

import br.com.fao.gitshare.data.model.Repo
import kotlinx.coroutines.flow.Flow

interface RepoRepository {
    suspend fun listRepositories(user: String) : Flow<List<Repo>>
}