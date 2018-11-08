package ar.com.wolox.android.cookbook.common.di

import android.app.Application
import ar.com.wolox.android.cookbook.CookbookApplication
import ar.com.wolox.android.cookbook.navigation.NavigationRecipeModule

import ar.com.wolox.android.cookbook.datasync.DataSyncRecipeModule
import ar.com.wolox.wolmo.core.di.modules.ContextModule
import ar.com.wolox.wolmo.core.di.modules.DefaultModule
import ar.com.wolox.wolmo.core.di.scopes.ApplicationScope

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@ApplicationScope
@Component(
        dependencies = [CookbookNetworkingComponent::class],
        modules = [
            AndroidSupportInjectionModule::class, DefaultModule::class, ContextModule::class,
            AppModule::class, NavigationRecipeModule::class, DataSyncRecipeModule::class
        ]
)
interface AppComponent : AndroidInjector<CookbookApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<CookbookApplication>() {

        @BindsInstance
        abstract fun application(application: Application): Builder

        @BindsInstance
        abstract fun sharedPreferencesName(sharedPreferencesName: String): Builder

        abstract fun networkingComponent(networkingComponent: CookbookNetworkingComponent): Builder
    }
}
