package ar.com.wolox.android.cookbook.common.network

import ar.com.wolox.wolmo.networking.retrofit.callback.NetworkCallback
import okhttp3.ResponseBody

open class SimpleNetworkCallback<T> : NetworkCallback<T>() {

    override fun onResponseSuccessful(p0: T?) {}

    override fun onResponseFailed(p0: ResponseBody?, p1: Int) {}

    override fun onCallFailure(p0: Throwable?) {}
}

class NetworkCallbackBuilder<T> {
    private var onResponseSuccessful: (T?) -> Unit = {}
    private var onResponseFailed: (ResponseBody?, Int) -> Unit = { _, _ -> }
    private var onCallFailure: (Throwable?) -> Unit = {}

    fun onResponseSuccessful(block: (T?) -> Unit) = apply { onResponseSuccessful = block }
    fun onResponseFailed(block: (ResponseBody?, Int) -> Unit) = apply { onResponseFailed = block }
    fun onCallFailure(block: (Throwable?) -> Unit) = apply { onCallFailure = block }

    fun build(): SimpleNetworkCallback<T> {
        return object : SimpleNetworkCallback<T>() {
            override fun onResponseSuccessful(p0: T?) {
                onResponseSuccessful.invoke(p0)
            }

            override fun onResponseFailed(p0: ResponseBody?, p1: Int) {
                onResponseFailed.invoke(p0, p1)
            }

            override fun onCallFailure(p0: Throwable?) {
                onCallFailure.invoke(p0)
            }
        }
    }
}

inline fun <T> networkCallback(block: NetworkCallbackBuilder<T>.() -> Unit) = NetworkCallbackBuilder<T>().apply(block).build()
