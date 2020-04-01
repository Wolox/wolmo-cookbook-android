package ar.com.wolox.android.cookbook.coroutines.examples.cooperativecancellation

import android.util.Log
import ar.com.wolox.android.cookbook.coroutines.examples.CoroutinesExampleBasePresenter
import ar.com.wolox.android.cookbook.coroutines.examples.CoroutinesExampleView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoroutinesExampleCooperativeCancellationPresenter @Inject constructor() : CoroutinesExampleBasePresenter<CoroutinesExampleView>() {

    fun onStartCooperativeCancellationRequested(isCooperative: Boolean) {
        if (isCooperative) {
            wasteCPU("Cooperative") { it <= 5 && isActive }
        } else {
            wasteCPU("NonCooperative") { it <= 5 }
        }
    }

    /**
     * Wastes CPU while the [until] is true.
     *
     * Since Coroutines cancellation is cooperative the CPU work couldn't be cancelled
     * and the code should cooperate by using the [isActive] flag.
     */
    private fun wasteCPU(loggingMessage: String, wasteWhile: CoroutineScope.(Int) -> Boolean) = launch {
        val startTime = System.currentTimeMillis()
        val job = launch(Dispatchers.Default) {
            try {
                var nextLoop = startTime
                var i = 0
                while (wasteWhile(i)) {
                    if (System.currentTimeMillis() >= nextLoop) {
                        nextLoop += 1_000
                        i++
                        Log.d(TAG, "$i second/s")
                    }
                }
            } finally {
                Log.d(TAG, "$loggingMessage -> Now it's cancelled")
            }
        }

        delay(1_500)

        Log.d(TAG, "$loggingMessage -> Cancel and wait")
        job.cancel()
    }

    companion object {
        private const val TAG = "COOPERATIVE_CANCEL"
    }
}
