package ar.com.wolox.android.cookbook

import ar.com.wolox.android.cookbook.koin.core.ToastFactory
import ar.com.wolox.android.cookbook.koin.koinLoginModule
import org.koin.core.KoinApplication
import org.koin.core.module.Module
import org.koin.dsl.module

object CookbookModules {
    private val appModule: Module = module {
        single { ToastFactory(get()) }
    }

    fun KoinApplication.initializeModules() = modules(
        appModule,
        koinLoginModule
    )
}
