package ar.com.wolox.android.cookbook.coroutines.examples.builders

import ar.com.wolox.android.cookbook.coroutines.examples.CoroutinesExampleBasePresenter
import ar.com.wolox.android.cookbook.coroutines.examples.CoroutinesExampleView
import ar.com.wolox.android.cookbook.coroutines.examples.D
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.math.roundToInt
import kotlin.random.Random
import kotlin.system.measureTimeMillis

class CoroutinesExampleBuildersPresenter @Inject constructor() : CoroutinesExampleBasePresenter<CoroutinesExampleView>() {

    fun onStartSequentialRequest() = startBuilderExample {
        (1..5).map { getRandomNumber() }
    }

    fun onStartConcurrentRequest() = startBuilderExample {
        (1..5).map { async { getRandomNumber() } }.awaitAll()
    }

    /**
     * Start a builder example by doing a transformation to a range of numbers and showing the average.
     *
     * By using [async] all the transformations will be done concurrently.
     * By using [launch] the transformations will be done sequentially, one after another.
     *
     * At last, it'll show the elapsed time of the executed transformation.
     */
    private fun startBuilderExample(takeRandomList: suspend () -> List<Int>) {
        launch {
            val time = measureTimeMillis {
                val avg = takeRandomList().average().roundToInt()
                log(D, "The average is $avg")
            }
            log(D, "It takes ${TimeUnit.MILLISECONDS.toSeconds(time)} seconds ($time ms) to run")
        }
    }

    /** Getting a random [Int] that takes 1 seconds. */
    private suspend fun getRandomNumber() = withContext(Dispatchers.Default) {
        delay(1000)
        Random.nextInt(0, 100).also { log(D, "Random number was $it") }
    }
}