package ar.com.wolox.android.cookbook.coroutines.examples.builders

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.coroutines.core.CoroutinesRecipeItemFragment
import kotlinx.android.synthetic.main.fragment_coroutines_builders.*
import javax.inject.Inject

class CoroutinesExampleBuildersFragment @Inject constructor() :
        CoroutinesRecipeItemFragment<CoroutinesExampleBuildersPresenter>() {

    override val childrenLayout = R.layout.fragment_coroutines_builders

    override val titleRes = R.string.coroutines_examples_builders_title

    override val descriptionRes = R.string.coroutines_examples_builders_description

    override fun setListeners() {
        sequentialButton.setOnClickListener { presenter.onStartSequentialRequest() }
        concurrentButton.setOnClickListener { presenter.onStartConcurrentRequest() }
    }
}