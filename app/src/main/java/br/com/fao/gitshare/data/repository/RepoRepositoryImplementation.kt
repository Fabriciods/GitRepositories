package br.com.fao.gitshare.data.repository

import br.com.fao.gitshare.core.RemoteException
import br.com.fao.gitshare.data.model.Repo
import br.com.fao.gitshare.data.services.GitHubService
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class RepoRepositoryImplementation(private val service:GitHubService): RepoRepository {
    override suspend fun listRepositories(user: String) = flow {
       try {
           val repoList = service.listRepositories(user)
           emit(repoList)
       }catch (ex : HttpException){
           throw RemoteException(ex.message ?: "Não foi possivel fazer a busca no momento!")
       }
    }
}