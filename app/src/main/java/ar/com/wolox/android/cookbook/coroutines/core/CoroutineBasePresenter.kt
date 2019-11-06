package ar.com.wolox.android.cookbook.coroutines.core

import androidx.annotation.CallSuper
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel

abstract class CoroutineBasePresenter<T> : BasePresenter<T>(), CoroutineScope by CoroutineScope(Dispatchers.Main) {

    @CallSuper
    override fun onViewDetached() {
        cancel()
        super.onViewDetached()
    }
}
