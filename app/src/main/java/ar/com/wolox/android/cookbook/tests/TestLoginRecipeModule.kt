package ar.com.wolox.android.cookbook.tests

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class TestLoginRecipeModule {

    @ContributesAndroidInjector
    abstract fun testLoginRecipeActivity(): TestLoginRecipeActivity

    @ContributesAndroidInjector
    abstract fun testLoginRecipeFragment(): TestLoginRecipeFragment
}