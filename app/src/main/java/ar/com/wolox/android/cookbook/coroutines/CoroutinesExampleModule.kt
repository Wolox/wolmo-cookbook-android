package ar.com.wolox.android.cookbook.coroutines

import ar.com.wolox.android.cookbook.coroutines.examples.builders.CoroutinesExampleBuildersFragment
import ar.com.wolox.android.cookbook.coroutines.examples.cascadecancellation.CoroutinesExampleCascadeCancellationFragment
import ar.com.wolox.android.cookbook.coroutines.examples.context.CoroutinesExampleContextLongRunningTaskFragment
import ar.com.wolox.android.cookbook.coroutines.examples.cooperativecancellation.CoroutinesExampleCooperativeCancellationFragment
import ar.com.wolox.android.cookbook.coroutines.examples.scope.CoroutinesExampleScopeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CoroutinesExampleModule {

    @ContributesAndroidInjector
    abstract fun coroutinesRecipeActivity(): CoroutinesRecipeActivity

    @ContributesAndroidInjector
    abstract fun coroutinesRecipeFragment(): CoroutinesRecipeFragment

    @ContributesAndroidInjector
    abstract fun coroutinesExampleCascadeCancellationFragment(): CoroutinesExampleCascadeCancellationFragment

    @ContributesAndroidInjector
    abstract fun coroutinesExampleCooperativeCancellationFragment(): CoroutinesExampleCooperativeCancellationFragment

    @ContributesAndroidInjector
    abstract fun coroutinesExampleBuildersFragment(): CoroutinesExampleBuildersFragment

    @ContributesAndroidInjector
    abstract fun coroutinesExampleContextLongRunningTaskFragment(): CoroutinesExampleContextLongRunningTaskFragment

    @ContributesAndroidInjector
    abstract fun coroutinesExampleScopeFragment(): CoroutinesExampleScopeFragment
}