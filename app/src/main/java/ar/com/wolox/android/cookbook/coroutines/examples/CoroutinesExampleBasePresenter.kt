package ar.com.wolox.android.cookbook.coroutines.examples

import ar.com.wolox.android.cookbook.coroutines.core.CoroutineBasePresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class CoroutinesExampleBasePresenter<T : CoroutinesExampleView> : CoroutineBasePresenter<T>() {

    /** Logging on the view will be performed on the main dispatcher. */
    protected suspend fun log(logType: LogType, text: String) = withContext(Dispatchers.Main) {
        view.log(logType, text)
    }
}