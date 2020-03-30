package ar.com.wolox.android.cookbook.analytics

import ar.com.wolox.android.cookbook.analytics.core.AnalyticsManager
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class LoremIpsumPresenter @Inject constructor(private val analyticsManager: AnalyticsManager) : BasePresenter<LoremIpsumView>() {

    fun onVisible() = view?.setCurrentScreen(analyticsManager)
}
