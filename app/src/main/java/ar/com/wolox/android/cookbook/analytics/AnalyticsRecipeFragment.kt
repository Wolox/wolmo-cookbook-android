package ar.com.wolox.android.cookbook.analytics

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.databinding.FragmentAnalyticsBinding
import ar.com.wolox.wolmo.core.adapter.viewpager.SimpleFragmentPagerAdapter
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class AnalyticsRecipeFragment : WolmoFragment<FragmentAnalyticsBinding, BasePresenter<Any>>() {

    @Inject
    internal lateinit var loremIpsumFragment: LoremIpsumFragment

    @Inject
    internal lateinit var showMyAgeFragment: ShowMyAgeFragment

    override fun layout() = R.layout.fragment_analytics

    override fun init() {
        binding!!.viewPager.adapter = SimpleFragmentPagerAdapter(childFragmentManager).apply {
            addFragments(
                showMyAgeFragment to getString(R.string.analytics_show_my_age_title),
                loremIpsumFragment to getString(R.string.analytics_lorem_ipsum_title))
        }
    }

    companion object {

        fun newInstance() = AnalyticsRecipeFragment()
    }
}