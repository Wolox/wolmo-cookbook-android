package ar.com.wolox.android.cookbook.coroutines.examples.context

import android.view.MotionEvent
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.coroutines.core.CoroutinesRecipeItemFragment
import kotlinx.android.synthetic.main.fragment_coroutines_context_long_running_task.*
import javax.inject.Inject

class CoroutinesExampleContextLongRunningTaskFragment @Inject constructor() :
        CoroutinesRecipeItemFragment<CoroutinesExampleContextLongRunningTaskPresenter>() {

    override val childrenLayout = R.layout.fragment_coroutines_context_long_running_task

    override val titleRes = R.string.coroutines_examples_context_long_task_title

    override val descriptionRes = R.string.coroutines_examples_context_long_task_description

    override fun init() {
        super.init()
        coolCat.setOnTouchListener { _, motionEvent ->
            if (motionEvent.actionMasked == MotionEvent.ACTION_MOVE || motionEvent.actionMasked == MotionEvent.ACTION_DOWN) {
                with(coolCat) {
                    parent.requestDisallowInterceptTouchEvent(true)
                    x = motionEvent.rawX - width / 2
                }
            }
            true
        }
    }

    override fun setListeners() {
        mainButton.setOnClickListener { presenter.onStartOnMainRequested() }
        defaultButton.setOnClickListener { presenter.onStartOnDefaultRequested() }
    }
}