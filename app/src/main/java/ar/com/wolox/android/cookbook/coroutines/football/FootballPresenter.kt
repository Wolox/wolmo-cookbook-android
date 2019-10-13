package ar.com.wolox.android.cookbook.coroutines.football

import ar.com.wolox.android.cookbook.coroutines.core.CoroutineBasePresenter

abstract class FootballPresenter : CoroutineBasePresenter<FootballView>() {

    abstract fun onRandomPlayerMatchesButtonClicked()

    abstract fun onRandomTeamsSquadsSequentialButtonClicked()

    abstract fun onRandomTeamsSquadsAsyncButtonClicked()

    companion object {
        const val SPAIN_COMPETITION_ID: Long = 2014
        const val MAX_REQUESTS = 10
        const val TOO_MANY_REQUESTS_ERROR_CODE = 429
    }
}
