package ar.com.wolox.android.cookbook.koin

import org.koin.dsl.module

val koinLoginModule = module {
    factory { KoinLoginRecipeFragment() }

    /** Get the view by parameter. */
    factory { (view: KoinLoginRecipeView) -> KoinLoginRecipePresenter(view, get()) }

    single { KoinLoginRecipeService() }
}
