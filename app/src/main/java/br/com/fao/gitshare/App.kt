package br.com.fao.gitshare

import android.app.Application
import br.com.fao.gitshare.data.di.DataModule
import br.com.fao.gitshare.domain.di.DomainModule
import br.com.fao.gitshare.presentation.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
        }
        DataModule.load()
        DomainModule.load()
        PresentationModule.load()
    }
}