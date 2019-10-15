package ar.com.wolox.android.cookbook.coroutines.examples

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit
import kotlin.math.roundToInt
import kotlin.random.Random
import kotlin.system.measureTimeMillis

fun CoroutinesExamplePresenter.startLaunchBuilderExample(view: CoroutinesExampleView) {
    startBuilderExample(view) {
        map { getRandomNumber() }
    }
}

fun CoroutinesExamplePresenter.startAsyncBuilderExample(view: CoroutinesExampleView) {
    startBuilderExample(view) {
        map { async { getRandomNumber() } }.awaitAll()
    }
}

private fun CoroutinesExamplePresenter.startBuilderExample(
    view: CoroutinesExampleView,
    transformation: suspend IntRange.() -> List<Int>
) = launch {
    val time = measureTimeMillis { view.showNumber((1..20).transformation().average().roundToInt()) }
    view.showMessage("It takes ${TimeUnit.MILLISECONDS.toSeconds(time)} seconds ($time ms) to run")
}

private suspend fun getRandomNumber() = withContext(Dispatchers.Default) {
    delay(500)
    Random.nextInt(0, 100)
}