package ar.com.wolox.android.cookbook.navigation

import ar.com.wolox.wolmo.core.di.scopes.ApplicationScope

import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

/**
 * Created by terrakok 24.11.16
 */

@Module
class NavigationModule {
    private val cicerone: Cicerone<Router> = Cicerone.create()

    @Provides
    @ApplicationScope
    internal fun provideRouter(): Router = cicerone.router

    @Provides
    @ApplicationScope
    internal fun provideNavigatorHolder(): NavigatorHolder = cicerone.navigatorHolder
}
