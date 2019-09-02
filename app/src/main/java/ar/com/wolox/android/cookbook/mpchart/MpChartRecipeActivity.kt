package ar.com.wolox.android.cookbook.mpchart

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.activity.WolmoActivity

/**
 * To implement MpAndroidChart:
 * 1. Add dependencies (See "Notes" at bottom)
 * 2. Generate DataSet
 * 3. Define graph type and properties
 *
 *  See notes in "MpChartRecipePresenter.kt" and "MpChartRecipeFragment.kt"
 */
class MpChartRecipeActivity : WolmoActivity() {

    override fun layout(): Int = R.layout.activity_base

    override fun init() = replaceFragment(R.id.vActivityBaseContent, MpChartRecipeFragment())
}

/**
 * Notes: // MpChart
 *   implementation "com.github.PhilJay:MPAndroidChart:v3.1.0"
 */