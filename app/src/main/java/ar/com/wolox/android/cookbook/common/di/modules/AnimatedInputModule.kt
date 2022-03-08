package ar.com.wolox.android.cookbook.common.di.modules

import ar.com.wolox.android.cookbook.animatedinput.AnimatedInputActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AnimatedInputModule {
    @ContributesAndroidInjector
    abstract fun animatedInputActivity(): AnimatedInputActivity
}