package ar.com.wolox.android.cookbook.coroutines.examples.cooperativecancellation

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.coroutines.core.CoroutinesRecipeItemFragment
import kotlinx.android.synthetic.main.fragment_coroutines_cooperative_cancellation.*
import javax.inject.Inject

class CoroutinesExampleCooperativeCancellationFragment @Inject constructor() :
        CoroutinesRecipeItemFragment<CoroutinesExampleCooperativeCancellationPresenter>() {

    override val childrenLayout = R.layout.fragment_coroutines_cooperative_cancellation

    override val titleRes = R.string.coroutines_examples_cooperative_cancellation_title

    override val descriptionRes = R.string.coroutines_examples_cooperative_cancellation_description

    override fun setListeners() {
        cooperativeButton.setOnClickListener { presenter.onStartCooperativeCancellationRequested(true) }
        nonCooperativeButton.setOnClickListener { presenter.onStartCooperativeCancellationRequested(false) }
    }
}