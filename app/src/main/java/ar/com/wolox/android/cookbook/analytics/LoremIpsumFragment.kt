package ar.com.wolox.android.cookbook.analytics

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.analytics.core.AnalyticsManager
import ar.com.wolox.android.cookbook.databinding.FragmentLoremImpsumBinding
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import javax.inject.Inject

class LoremIpsumFragment @Inject constructor() :
    WolmoFragment<FragmentLoremImpsumBinding, LoremIpsumPresenter>(), LoremIpsumView {

    override fun layout() = R.layout.fragment_lorem_impsum

    override fun init() {
    }

    override fun onVisible() {
        presenter.onVisible()
    }

    override fun setCurrentScreen(analyticsManager: AnalyticsManager) =
        analyticsManager.setCurrentScreen(this)
}