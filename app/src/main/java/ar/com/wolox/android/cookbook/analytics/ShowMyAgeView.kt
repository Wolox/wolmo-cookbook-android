package ar.com.wolox.android.cookbook.analytics

import ar.com.wolox.android.cookbook.analytics.core.AnalyticsManager

interface ShowMyAgeView {

    fun setCurrentScreen(analyticsManager: AnalyticsManager)

    fun openHelp()

    fun showEmailEmptyError()

    fun showPasswordEmptyError()

    fun showAge(name: String, age: Int)

    fun showInvalidUserError()

    fun showServeUnavailableError()
}