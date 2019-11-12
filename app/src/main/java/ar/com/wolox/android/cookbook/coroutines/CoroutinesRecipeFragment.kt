package ar.com.wolox.android.cookbook.coroutines

import androidx.core.util.Pair
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.coroutines.examples.builders.CoroutinesExampleBuildersFragment
import ar.com.wolox.android.cookbook.coroutines.examples.cascadecancellation.CoroutinesExampleCascadeCancellationFragment
import ar.com.wolox.android.cookbook.coroutines.examples.context.CoroutinesExampleContextLongRunningTaskFragment
import ar.com.wolox.android.cookbook.coroutines.examples.cooperativecancellation.CoroutinesExampleCooperativeCancellationFragment
import ar.com.wolox.android.cookbook.coroutines.examples.scope.CoroutinesExampleScopeFragment
import ar.com.wolox.wolmo.core.adapter.viewpager.SimpleFragmentPagerAdapter
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import kotlinx.android.synthetic.main.fragment_coroutines_example.*
import javax.inject.Inject

class CoroutinesRecipeFragment : WolmoFragment<BasePresenter<Any>>() {

    @Inject
    internal lateinit var builders: CoroutinesExampleBuildersFragment

    @Inject
    internal lateinit var contextLongTask: CoroutinesExampleContextLongRunningTaskFragment

    @Inject
    internal lateinit var scope: CoroutinesExampleScopeFragment

    @Inject
    internal lateinit var cascadeCancellation: CoroutinesExampleCascadeCancellationFragment

    @Inject
    internal lateinit var cooperativeCancellation: CoroutinesExampleCooperativeCancellationFragment

    override fun layout() = R.layout.fragment_coroutines_example

    override fun init() {
        viewPager.adapter = SimpleFragmentPagerAdapter(childFragmentManager).apply {
            addFragments(
                    Pair(builders, ""),
                    Pair(contextLongTask, ""),
                    Pair(scope, ""),
                    Pair(cascadeCancellation, ""),
                    Pair(cooperativeCancellation, ""))
        }
    }

    companion object {
        fun newInstance() = CoroutinesRecipeFragment()
    }
}