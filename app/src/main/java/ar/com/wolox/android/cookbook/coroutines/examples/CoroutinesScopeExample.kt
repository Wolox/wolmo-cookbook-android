package ar.com.wolox.android.cookbook.coroutines.examples

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Example that shows the different usage of the scopes.
 *
 * Output:
 *
 * Global - 0 second/s
 * Presenter - 0 second/s
 * NonCancellable - 0 second/s
 *
 * Global - 1 second/s
 * Presenter - 1 second/s
 * NonCancellable - 1 second/s
 *
 * Global - 2 second/s
 * Presenter - 2 second/s
 * NonCancellable - 2 second/s
 *
 * Destroy view
 *
 * Global - 3 second/s
 * NonCancellable - 3 second/s
 *
 * Global - 4 second/s
 * NonCancellable - 4 second/s
 */
fun CoroutinesExamplePresenter.startScopeExample(view: CoroutinesExampleView) {

    // This'll run until the application stops.
    GlobalScope.launch(Dispatchers.Default) { logSeconds("Global") }

    // This'll run until view's destroyed.
    launch(Dispatchers.Default) { logSeconds("Presenter") }

    // This'll run until the application stops because it's non cancellable.
    launch(Dispatchers.Default + NonCancellable) { logSeconds("NonCancellable") }

    // Wait three seconds to destroy view.
    launch(Dispatchers.Default) {
        delay(3_000)
        withContext(Dispatchers.Main) {
            Log.d(TAG, "Destroy view")
            view.closeView()
        }
    }
}

private suspend fun logSeconds(message: String, times: Int = 5) = repeat(times) {
    Log.d(TAG, "$message - $it second/s")
    delay(1_000)
}

private const val TAG = "COROUTINES_EXAMPLE"