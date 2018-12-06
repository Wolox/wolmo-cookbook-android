package ar.com.wolox.android.cookbook.common.di

import ar.com.wolox.wolmo.core.di.scopes.ApplicationScope
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named

@Module
class RxJava2Module {

    @Module
    companion object {

        @JvmStatic
        @ApplicationScope
        @Provides
        @Named("main")
        internal fun provideMainThreadScheduler() = AndroidSchedulers.mainThread()!!

        @JvmStatic
        @ApplicationScope
        @Provides
        @Named("background")
        internal fun provideBackgroundThreadScheduler() = Schedulers.io()
    }
}