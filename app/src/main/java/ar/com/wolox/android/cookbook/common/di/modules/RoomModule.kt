package ar.com.wolox.android.cookbook.common.di.modules

import ar.com.wolox.android.cookbook.room.RoomRecipeActivity
import ar.com.wolox.android.cookbook.room.RoomRecipeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class RoomModule {

    @ContributesAndroidInjector
    abstract fun roomActivity(): RoomRecipeActivity

    @ContributesAndroidInjector
    abstract fun roomFragment(): RoomRecipeFragment
}