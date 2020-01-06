package ar.com.wolox.android.cookbook.coroutines.examples.scope

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.coroutines.examples.CoroutinesExampleView
import ar.com.wolox.android.cookbook.coroutines.core.CoroutinesRecipeItemFragment
import kotlinx.android.synthetic.main.fragment_coroutines_scope.*
import javax.inject.Inject

interface CoroutinesExampleScopeView : CoroutinesExampleView {

    fun close()
}

class CoroutinesExampleScopeFragment @Inject constructor() :
        CoroutinesRecipeItemFragment<CoroutinesExampleScopePresenter>(), CoroutinesExampleScopeView {

    override val childrenLayout = R.layout.fragment_coroutines_scope

    override val titleRes = R.string.coroutines_examples_scope_title

    override val descriptionRes = R.string.coroutines_examples_scope_description

    override fun setListeners() {
        globalButton.setOnClickListener { presenter.onGlobalRequested() }
        presenterButton.setOnClickListener { presenter.onPresenterRequested() }
    }

    override fun close() = requireActivity().finish()
}