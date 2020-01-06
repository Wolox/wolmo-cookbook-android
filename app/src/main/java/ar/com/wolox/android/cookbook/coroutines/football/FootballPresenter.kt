package ar.com.wolox.android.cookbook.coroutines.football

import ar.com.wolox.wolmo.core.presenter.CoroutineBasePresenter
import okhttp3.ResponseBody

abstract class FootballPresenter : CoroutineBasePresenter<FootballView>() {

    abstract fun onRandomPlayerMatchesButtonClicked()

    abstract fun onRandomTeamsSquadsSequentialButtonClicked()

    abstract fun onRandomTeamsSquadsAsyncButtonClicked()

    protected fun handleError(body: ResponseBody?, code: Int) {
        if (code == TOO_MANY_REQUESTS_ERROR_CODE) {
            view?.showTooManyRequestsError()
        } else {
            view?.showUnexpectedError()
        }
    }

    companion object {
        const val SPAIN_COMPETITION_ID: Long = 2014
        const val MAX_REQUESTS = 3
        const val TOO_MANY_REQUESTS_ERROR_CODE = 429
    }
}
