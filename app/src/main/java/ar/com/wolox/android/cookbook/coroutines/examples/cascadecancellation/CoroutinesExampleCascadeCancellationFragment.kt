package ar.com.wolox.android.cookbook.coroutines.examples.cascadecancellation

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.coroutines.core.CoroutinesRecipeItemFragment
import kotlinx.android.synthetic.main.fragment_coroutines_cascade_cancellation.*
import javax.inject.Inject

class CoroutinesExampleCascadeCancellationFragment @Inject constructor() :
        CoroutinesRecipeItemFragment<CoroutinesExampleCascadeCancellationPresenter>() {

    override val childrenLayout = R.layout.fragment_coroutines_cascade_cancellation

    override val titleRes = R.string.coroutines_examples_cascade_cancellation_title

    override val descriptionRes = R.string.coroutines_examples_cascade_cancellation_description

    override fun setListeners() {
        cancelJobAButton.setOnClickListener { presenter.onStartCancellationRequested(JobToCancel.A) }
        cancelJobBButton.setOnClickListener { presenter.onStartCancellationRequested(JobToCancel.B) }
        cancelJobCButton.setOnClickListener { presenter.onStartCancellationRequested(JobToCancel.C) }
    }
}