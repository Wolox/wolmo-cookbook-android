package ar.com.wolox.android.cookbook

import ar.com.wolox.android.cookbook.koin.KoinLoginRecipeService
import ar.com.wolox.android.cookbook.koin.core.ToastFactory
import ar.com.wolox.android.cookbook.koin.koinLoginModule
import org.koin.core.KoinApplication
import org.koin.core.module.Module
import org.koin.dsl.module

object CookbookModules {
    /** Injections for all the application. */
    private val appModule: Module = module {
        /** A single instance will be created for all injection. */
        single { ToastFactory(get()) }
    }

    /** Service injections for all the application. */
    private val servicesModule: Module = module {
        /** A single instance will be created for all injection. */
        single { KoinLoginRecipeService() }
    }

    fun KoinApplication.initializeModules() = modules(listOf(
        appModule,
        servicesModule,
        koinLoginModule
    ))
}
