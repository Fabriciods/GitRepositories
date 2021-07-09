package br.com.fao.gitshare.data.di

import android.util.Log
import br.com.fao.gitshare.data.repository.RepoRepository
import br.com.fao.gitshare.data.repository.RepoRepositoryImplementation
import br.com.fao.gitshare.data.services.GitHubService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataModule {
    private const val OK_HTPP = "okHttp"
    fun load() {
        loadKoinModules(networkModules() + repositoriesModule())
    }

    private fun networkModules(): Module {
        return module {
            //retorna a mesma instancia o oposto seria o factory ue cria uma nova instancia.
            single {
                val interceptor = HttpLoggingInterceptor {
                    Log.e(OK_HTPP, it)
                }
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                OkHttpClient.Builder().addInterceptor(interceptor).build()
            }
            single {
                GsonConverterFactory.create(GsonBuilder().create())
            }
            single {
                createService<GitHubService>(get(), get())
            }
        }
    }

    private fun repositoriesModule(): Module {
        return module {
            single<RepoRepository> {
                RepoRepositoryImplementation(get())
            }
        }
    }

    private inline fun <reified T> createService(
        client: OkHttpClient,
        factory: GsonConverterFactory
    ): T {
        return Retrofit.Builder().baseUrl("https://api.github.com/")
            .client(client)
            .addConverterFactory(factory)
            .build().create(T::class.java)
    }
}