package ar.com.wolox.android.cookbook.coroutines.examples

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

sealed class JobToCancel
object JobA : JobToCancel()
object JobB : JobToCancel()
object JobC : JobToCancel()

fun CoroutinesExamplePresenter.startChildrenCancellation(view: CoroutinesExampleView) {
    launch(Dispatchers.Default) {
        launchThreeJobsAndCancel("Cancelling C", JobC)
        launchThreeJobsAndCancel("Cancelling B", JobB)
        launchThreeJobsAndCancel("Cancelling A", JobA)
    }
}

fun CoroutinesExamplePresenter.startCooperativeCancellation(view: CoroutinesExampleView) {
    launch(Dispatchers.Default) {
        wasteCPU("NonCooperative") { it < System.currentTimeMillis() }
        wasteCPU("Cooperative") { it < System.currentTimeMillis() && isActive }
    }
}

/**
 * Launches three different jobs that logs the seconds and cancels a [jobToCancel].
 *
 * If the job A is cancelled, then the three of them'll be cancelled too.
 * If the job B is cancelled, then the job C will be cancelled too.
 * If the job C is cancelled, then just it will be cancelled.
 */
private suspend fun CoroutinesExamplePresenter.launchThreeJobsAndCancel(exampleName: String, jobToCancel: JobToCancel) {

    lateinit var jobA: Job
    lateinit var jobB: Job
    lateinit var jobC: Job

    Log.d(TAG, "Starting example $exampleName")

    jobA = launch {
        jobB = launch {
            jobC = launch {
                logSeconds("Job C")
            }
            logSeconds("Job B")
        }
        logSeconds("Job A")
    }

    delay(2_000)

    when (jobToCancel) {
        JobA -> jobA.cancel()
        JobB -> jobB.cancel()
        JobC -> jobC.cancel()
    }

    jobA.join()
}

/**
 * Wastes CPU while the [until] is true.
 *
 * Since Coroutines cancellation is cooperative the CPU work couldn't be cancelled
 * and the code should cooperate by using the [isActive] flag.
 */
private suspend fun CoroutinesExamplePresenter.wasteCPU(loggingMessage: String, until: CoroutineScope.(Long) -> Boolean) {
    val finishTime = System.currentTimeMillis() + 5_000
    val job = launch(Dispatchers.Default) {
        while (until(finishTime)) {
            Log.d(TAG, "$loggingMessage -> Wasting CPU ${System.currentTimeMillis()}")
        }
    }

    delay(2_000)

    Log.d(TAG, "Cancel and wait")
    job.cancelAndJoin()

    Log.d(TAG, "Now it's cancelled")
}

private suspend fun logSeconds(message: String, times: Int = 5) = repeat(times) {
    Log.d(TAG, "$message - $it second/s")
    delay(1_000)
}

private const val TAG = "CANCELLATION"