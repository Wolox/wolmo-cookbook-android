package ar.com.wolox.android.cookbook.notifications.di

import ar.com.wolox.android.cookbook.notifications.NotificationFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class NotificationModule {

    @ContributesAndroidInjector
    abstract fun notificationFragment(): NotificationFragment
}