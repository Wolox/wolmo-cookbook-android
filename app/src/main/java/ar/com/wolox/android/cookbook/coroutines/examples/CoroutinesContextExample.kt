package ar.com.wolox.android.cookbook.coroutines.examples

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class CoroutinesContextExample(private val view: CoroutinesExampleView) {

    val longRunningTask = LongRunningTask()
    val changeView = ChangeView()

    /**
     * The long running tasks should not be performed on the main thread.
     */
    inner class LongRunningTask {

        private suspend fun perform(context: CoroutineContext) = withContext(context) {
            for (i in 1 until 10000) {
                for (j in 1 until 10000) {
                    Log.d(TAG, "$i -> $j")
                }

                withContext(Dispatchers.Main) {
                    view.showNumber(i)
                }
            }
        }

        /** This freezes the application and'll probably crash. */
        suspend fun fromMainContext() = perform(Dispatchers.Default)

        /**
         * This performs the long running task on the background while
         * the application is being used.
         */
        suspend fun fromDefaultContext() = perform(Dispatchers.Default)
    }

    /**
     * Changes the view should be done from the Main context.
     */
    inner class ChangeView {

        private suspend fun change(context: CoroutineContext) = withContext(context) {
            view.showNumber(100)
        }

        /**
         * Throws a CalledFromWrongThreadException with the message:
         * "Only the original thread that created a view hierarchy can touch its views."
         */
        suspend fun fromDefaultContext() = change(Dispatchers.Default)

        /** This changes the view without problems. */
        suspend fun fromMainContext() = change(Dispatchers.Main)
    }

    companion object {
        private const val TAG = "COROUTINES_EXAMPLE"
    }

    enum class ContextOptio
}