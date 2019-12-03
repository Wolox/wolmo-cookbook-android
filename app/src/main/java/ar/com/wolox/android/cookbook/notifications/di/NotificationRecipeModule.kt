package ar.com.wolox.android.cookbook.notifications.di

import ar.com.wolox.android.cookbook.notifications.NotificationActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class NotificationRecipeModule {

    @NotificationScope
    @ContributesAndroidInjector(modules = [NotificationModule::class])
    abstract fun notificationRecipeActivity(): NotificationActivity
}
