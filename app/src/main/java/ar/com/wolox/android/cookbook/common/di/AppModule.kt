package ar.com.wolox.android.cookbook.common.di

import ar.com.wolox.android.cookbook.WolmoTestActivity
import ar.com.wolox.android.cookbook.facebooklogin.FacebookLoginRecipeActivity
import ar.com.wolox.android.cookbook.facebooklogin.FacebookLoginRecipeFragment
import ar.com.wolox.android.cookbook.googlelogin.GoogleLoginRecipeActivity
import ar.com.wolox.android.cookbook.googlelogin.GoogleLoginRecipeFragment
import ar.com.wolox.android.cookbook.graphQl.OrdersActivity
import ar.com.wolox.android.cookbook.graphQl.OrdersFragment
import ar.com.wolox.android.cookbook.maps.MapActivity
import ar.com.wolox.android.cookbook.maps.MapFragment
import ar.com.wolox.android.cookbook.recipepicker.RecipePickerActivity
import ar.com.wolox.android.cookbook.recipepicker.RecipePickerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppModule {

    @ContributesAndroidInjector
    abstract fun testActivity(): WolmoTestActivity

    @ContributesAndroidInjector
    abstract fun recipePickerActivity(): RecipePickerActivity

    @ContributesAndroidInjector
    abstract fun recipePickerFragment(): RecipePickerFragment

    @ContributesAndroidInjector
    abstract fun googleLoginActivity(): GoogleLoginRecipeActivity

    @ContributesAndroidInjector
    abstract fun googleLoginFragment(): GoogleLoginRecipeFragment

    @ContributesAndroidInjector
    abstract fun facebookLoginActivity(): FacebookLoginRecipeActivity

    @ContributesAndroidInjector
    abstract fun facebookLoginFragment(): FacebookLoginRecipeFragment

    @ContributesAndroidInjector
    abstract fun mapActivity(): MapActivity

    @ContributesAndroidInjector
    abstract fun mapFragment(): MapFragment

    @ContributesAndroidInjector
    abstract fun graphQlActivity(): OrdersActivity

    @ContributesAndroidInjector
    abstract fun graphQlFragment(): OrdersFragment
}
