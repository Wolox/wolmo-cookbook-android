package ar.com.wolox.android.cookbook.common.di.modules

import ar.com.wolox.android.cookbook.mpchart.MpChartRecipeActivity
import ar.com.wolox.android.cookbook.mpchart.MpChartRecipeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ChartModule {

    @ContributesAndroidInjector
    abstract fun mpChartActivity(): MpChartRecipeActivity

    @ContributesAndroidInjector
    abstract fun mpChartFragment(): MpChartRecipeFragment
}