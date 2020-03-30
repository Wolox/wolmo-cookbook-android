package ar.com.wolox.android.cookbook.analytics

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class AnalyticsRecipeModule {

    @ContributesAndroidInjector
    abstract fun analyticsRecipeActivity(): AnalyticsRecipeActivity

    @ContributesAndroidInjector
    abstract fun analyticsRecipeFragment(): AnalyticsRecipeFragment

    @ContributesAndroidInjector
    abstract fun showMyAgeFragment(): ShowMyAgeFragment

    @ContributesAndroidInjector
    abstract fun loremIpsumFragment(): LoremIpsumFragment
}

@Module
object AnalyticsRepositoryModule {

    @Provides
    @JvmStatic
    fun usersRepository(): UserRepository = UserRepositoryImpl()
}