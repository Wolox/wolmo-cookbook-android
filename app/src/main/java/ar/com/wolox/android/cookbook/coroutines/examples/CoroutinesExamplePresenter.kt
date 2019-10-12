package ar.com.wolox.android.cookbook.coroutines.examples

import androidx.annotation.StringRes
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.coroutines.core.CoroutineBasePresenter
import ar.com.wolox.android.cookbook.coroutines.core.unit
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoroutinesExamplePresenter @Inject constructor() : CoroutineBasePresenter<CoroutinesExampleView>() {

    private lateinit var contextExample: CoroutinesContextExample

    override fun onViewAttached() {
        contextExample = CoroutinesContextExample(view)
        view.showOptions(CoroutinesExampleOption.values().toList())
    }

    fun onOptionSelected(option: CoroutinesExampleOption) = when (option) {

        CoroutinesExampleOption.SCOPE ->
            startScopeExample(view)

        CoroutinesExampleOption.CONTEXT_LONG_RUNNING_TASK_DEFAULT ->
            launch { contextExample.longRunningTask.fromDefaultContext() }.unit

        CoroutinesExampleOption.CONTEXT_LONG_RUNNING_TASK_MAIN ->
            launch { contextExample.longRunningTask.fromMainContext() }.unit

        CoroutinesExampleOption.CONTEXT_CHANGE_VIEW_DEFAULT ->
            launch { contextExample.changeView.fromDefaultContext() }.unit

        CoroutinesExampleOption.CONTEXT_CHANGE_VIEW_MAIN ->
            launch { contextExample.changeView.fromMainContext() }.unit
    }

    enum class CoroutinesExampleOption(@StringRes titleRes: Int) {
        SCOPE(R.string.app_title),
        CONTEXT_LONG_RUNNING_TASK_DEFAULT(R.string.app_title),
        CONTEXT_LONG_RUNNING_TASK_MAIN(R.string.app_title),
        CONTEXT_CHANGE_VIEW_DEFAULT(R.string.app_title),
        CONTEXT_CHANGE_VIEW_MAIN(R.string.app_title)
    }
}