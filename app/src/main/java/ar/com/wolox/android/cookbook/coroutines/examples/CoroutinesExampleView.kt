package ar.com.wolox.android.cookbook.coroutines.examples

interface CoroutinesExampleView {

    fun log(type: LogType, message: String)
}

sealed class LogType(val text: String)
object D : LogType("D")
object I : LogType("I")
object E : LogType("E")
object W : LogType("W")
