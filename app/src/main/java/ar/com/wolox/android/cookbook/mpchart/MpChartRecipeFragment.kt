package ar.com.wolox.android.cookbook.mpchart

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment

class MpChartRecipeFragment : WolmoFragment<MpChartRecipePresenter>(), MpChartRecipeView {

    override fun layout(): Int = R.layout.fragment_mp_chart

    override fun init() {
    }
}