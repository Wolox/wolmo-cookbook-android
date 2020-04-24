package ar.com.wolox.android.cookbook.common.di.modules

import ar.com.wolox.android.cookbook.signup.SignUpRecipeActivity
import ar.com.wolox.android.cookbook.signup.SignUpRecipeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SignUpModule {

    @ContributesAndroidInjector
    abstract fun signUpActivity(): SignUpRecipeActivity

    @ContributesAndroidInjector
    abstract fun signUpFragment(): SignUpRecipeFragment
}