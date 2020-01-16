package ar.com.wolox.android.cookbook.coroutines.examples.context

import android.util.Log
import ar.com.wolox.android.cookbook.coroutines.examples.CoroutinesExampleBasePresenter
import ar.com.wolox.android.cookbook.coroutines.examples.CoroutinesExampleView
import ar.com.wolox.android.cookbook.coroutines.examples.D
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class CoroutinesExampleContextLongRunningTaskPresenter @Inject constructor() : CoroutinesExampleBasePresenter<CoroutinesExampleView>() {

    fun onStartOnMainRequested() = start(Dispatchers.Main)

    fun onStartOnDefaultRequested() = start(Dispatchers.Default)

    /**
     * The long running tasks should not be performed on the main context.
     *
     * If it's run on main context, the application and'll probably crash.
     * If it's run on default context, the task'll be performed on background and
     * it'll be possible to use the application while it's happening.
     */
    fun start(context: CoroutineContext) = launch(context) {
        for (i in 1 until 15) {
            log(D, "$i. Starting")
            for (j in 1 until 10000) {
                Log.d(TAG, "Just wasting time: $i -> $j")
            }
            log(D, "$i. Finish")
        }
    }

    companion object {
        private const val TAG = "CONTEXT_LONG_TASK"
    }
}
