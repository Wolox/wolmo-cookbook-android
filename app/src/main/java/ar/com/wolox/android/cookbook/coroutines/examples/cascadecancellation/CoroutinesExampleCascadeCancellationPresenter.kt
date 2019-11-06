package ar.com.wolox.android.cookbook.coroutines.examples.cascadecancellation

import ar.com.wolox.android.cookbook.coroutines.examples.CoroutinesExampleBasePresenter
import ar.com.wolox.android.cookbook.coroutines.examples.CoroutinesExampleView
import ar.com.wolox.android.cookbook.coroutines.examples.D
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

enum class JobToCancel { A, B, C }

class CoroutinesExampleCascadeCancellationPresenter @Inject constructor() : CoroutinesExampleBasePresenter<CoroutinesExampleView>() {

    fun onStartCancellationRequested(jobToCancel: JobToCancel) = launch(Dispatchers.Default) {
        launchThreeJobsAndCancel(jobToCancel)
    }

    /** Log seconds by a [message] for [TIMES_TO_LOG_SECONDS] times. */
    private suspend fun logSeconds(message: String) = withContext(Dispatchers.Default) {
        repeat(TIMES_TO_LOG_SECONDS) {
            log(D, "$message - $it second/s")
            delay(1_000)
        }
    }

    /**
     * Launches three different jobs that logs the seconds and cancels a [jobToCancel].
     *
     * If the job A is cancelled, then the three of them'll be cancelled too.
     * If the job B is cancelled, then the job C will be cancelled too.
     * If the job C is cancelled, then just it will be cancelled.
     */
    private suspend fun launchThreeJobsAndCancel(jobToCancel: JobToCancel) {

        lateinit var jobA: Job
        lateinit var jobB: Job
        lateinit var jobC: Job

        log(D, "\n\n******************************\nStarting example\n******************************\n")

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

        log(D, "Now we'll cancel the $jobToCancel")
        when (jobToCancel) {
            JobToCancel.A -> jobA.cancel()
            JobToCancel.B -> jobB.cancel()
            JobToCancel.C -> jobC.cancel()
        }

        jobA.join()
    }

    companion object {
        private const val TIMES_TO_LOG_SECONDS = 5
    }
}
