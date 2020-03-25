package ar.com.wolox.android.cookbook.analytics

interface AnalyticsRecipeView {

    fun openHelp()

    fun showEmailEmptyError()

    fun showPasswordEmptyError()

    fun showAge(name: String, age: Int)

    fun showInvalidUserError()

    fun showServeUnavailableError()
}