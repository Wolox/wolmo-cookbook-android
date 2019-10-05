package ar.com.wolox.android.cookbook.coroutines.core

import androidx.annotation.CallSuper
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel

abstract class CoroutineBasePresenter<T> : BasePresenter<T>(), CoroutineScope {

    private val job = Job() + Dispatchers.Main

    override val coroutineContext = job

    @CallSuper
    override fun onViewDetached() {
        job.cancel()
        super.onViewDetached()
    }
}
