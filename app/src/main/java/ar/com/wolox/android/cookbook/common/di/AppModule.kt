package ar.com.wolox.android.cookbook.common.di

import ar.com.wolox.android.cookbook.googlelogin.GoogleLoginRecipeActivity
import ar.com.wolox.android.cookbook.googlelogin.GoogleLoginRecipeFragment
import ar.com.wolox.android.cookbook.navigation.NavigationActivity
import ar.com.wolox.android.cookbook.navigation.Navigation1Fragment
import ar.com.wolox.android.cookbook.navigation.Navigation2Fragment
import ar.com.wolox.android.cookbook.navigation.Navigation3Fragment
import ar.com.wolox.android.cookbook.navigation.Navigation4Fragment
import ar.com.wolox.android.cookbook.navigation.NavigationActivity
import ar.com.wolox.android.cookbook.navigation.NewNavigationActivity
import ar.com.wolox.android.cookbook.navigation.NewNavigationFragment
import ar.com.wolox.android.cookbook.recipepicker.RecipePickerActivity
import ar.com.wolox.android.cookbook.recipepicker.RecipePickerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppModule {

    @ContributesAndroidInjector
    abstract fun recipePickerActivity(): RecipePickerActivity

    @ContributesAndroidInjector
    abstract fun recipePickerFragment(): RecipePickerFragment

    @ContributesAndroidInjector
    abstract fun googleLoginActivity(): GoogleLoginRecipeActivity

    @ContributesAndroidInjector
    abstract fun googleLoginFragment(): GoogleLoginRecipeFragment

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
