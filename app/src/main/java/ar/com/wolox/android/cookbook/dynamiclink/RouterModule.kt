package ar.com.wolox.android.cookbook.dynamiclink

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class RouterModule {

    @ContributesAndroidInjector
    abstract fun contributesRouterActivity(): RouterActivity

    @ContributesAndroidInjector
    abstract fun ContributesRouterFragment(): RouterFragment
}