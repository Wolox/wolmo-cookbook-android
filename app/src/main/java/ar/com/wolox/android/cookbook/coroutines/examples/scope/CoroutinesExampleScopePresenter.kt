package ar.com.wolox.android.cookbook.coroutines.examples.scope

import android.util.Log
import ar.com.wolox.android.cookbook.coroutines.examples.CoroutinesExampleBasePresenter
import ar.com.wolox.android.cookbook.coroutines.examples.D
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CoroutinesExampleScopePresenter @Inject constructor() : CoroutinesExampleBasePresenter<CoroutinesExampleScopeView>() {

    fun onGlobalRequested() = start(GlobalScope)

    fun onPresenterRequested() = start(this)

    /**
     * Start a Coroutine that logs five seconds on the given [scope].
     * After two seconds, the view is destroyed.
     */
    private fun start(scope: CoroutineScope) = launch(Dispatchers.Default) {
        scope.launch(Dispatchers.Default) { logSeconds() }
        delay(2_500)
        log(D, "Destroy view")
        withContext(Dispatchers.Main) {
            view.close()
        }
    }

    private suspend fun logSeconds() = repeat(5) {
        try {
            log(D, "$it second/s")
        } catch (exception: Exception) {
            Log.e(TAG, "$it second/s", exception)
        }
        delay(1_000)
    }

    companion object {
        private const val TAG = "SCOPE_EXAMPLE"
    }
}