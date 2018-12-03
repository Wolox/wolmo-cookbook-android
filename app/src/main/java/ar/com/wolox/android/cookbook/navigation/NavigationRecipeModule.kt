package ar.com.wolox.android.cookbook.navigation

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class NavigationRecipeModule {

    @ContributesAndroidInjector
    abstract fun navigationActivity(): NavigationActivity

    @ContributesAndroidInjector
    abstract fun navigation1fragment(): Navigation1Fragment

    @ContributesAndroidInjector
    abstract fun navigation2fragment(): Navigation2Fragment

    @ContributesAndroidInjector
    abstract fun navigation3fragment(): Navigation3Fragment

    @ContributesAndroidInjector
    abstract fun navigation4fragment(): Navigation4Fragment

    @ContributesAndroidInjector
    abstract fun newNavigationctivity(): NewNavigationActivity

    @ContributesAndroidInjector
    abstract fun newNavigationFragment(): NewNavigationFragment
}
