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

    enum class CoroutinesExampleOption(@StringRes val titleRes: Int) {
        SCOPE(R.string.coroutines_examples_scope),
        CONTEXT_LONG_RUNNING_TASK_DEFAULT(R.string.coroutines_examples_context_long_running_task_default),
        CONTEXT_LONG_RUNNING_TASK_MAIN(R.string.coroutines_examples_context_long_running_task_main),
        CONTEXT_CHANGE_VIEW_DEFAULT(R.string.coroutines_examples_context_change_view_default),
        CONTEXT_CHANGE_VIEW_MAIN(R.string.coroutines_examples_context_change_view_main)
    }
}