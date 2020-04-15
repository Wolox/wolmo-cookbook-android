package ar.com.wolox.android.cookbook.common.di

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named

@Module
object CoroutineDispatchersModule {

    const val IO = "DISPATCHER_IO"
    const val DEFAULT = "DISPATCHER_DEFAULT"
    const val MAIN = "DISPATCHER_MAIN"
    const val UNCONFINED = "DISPATCHER_UNCONFINED"

    @JvmStatic
    @Provides
    @Named(IO)
    fun providesDispatcherIO(): CoroutineDispatcher = Dispatchers.IO

    @JvmStatic
    @Provides
    @Named(DEFAULT)
    fun providesDispatcherDefault(): CoroutineDispatcher = Dispatchers.Default

    @JvmStatic
    @Provides
    @Named(MAIN)
    fun providesDispatcherMain(): CoroutineDispatcher = Dispatchers.Main

    @JvmStatic
    @Provides
    @Named(UNCONFINED)
    fun providesDispatcherUnconfined(): CoroutineDispatcher = Dispatchers.Unconfined
}