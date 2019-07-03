package ar.com.wolox.android.cookbook.koin

import org.koin.core.qualifier.named
import org.koin.dsl.module

val koinLoginModule = module {

    /** One instance will be created for each injection. */
    factory { KoinLoginRecipeFragment() }

    /** This scoped will help to avoid memory leaks by binding the presenter to the fragment lifetime. */
    scope(named<KoinLoginRecipeFragment>()) {
        /** Get the view by parameter. */
        scoped { (view: KoinLoginRecipeView) -> KoinLoginRecipePresenter(view, get()) }
    }

    /** A single instance will be created for all injection. */
    single { KoinLoginRecipeService() }
}
