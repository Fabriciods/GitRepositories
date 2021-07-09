package br.com.fao.gitshare.domain

import br.com.fao.gitshare.core.UseCase
import br.com.fao.gitshare.data.model.Repo
import br.com.fao.gitshare.data.repository.RepoRepository
import kotlinx.coroutines.flow.Flow

class ListUserRepositoriesUseCase(private val repository: RepoRepository) :
    UseCase<String, List<Repo>>() {
    override suspend fun execute(param: String): Flow<List<Repo>> {
        return repository.listRepositories(param)
    }

}