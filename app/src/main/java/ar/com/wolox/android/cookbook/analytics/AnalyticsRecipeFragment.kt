package ar.com.wolox.android.cookbook.analytics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.databinding.FragmentAnalyticsBinding
import ar.com.wolox.wolmo.core.adapter.viewpager.SimpleFragmentPagerAdapter
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class AnalyticsRecipeFragment : WolmoFragment<BasePresenter<Any>>() {

    @Inject
    internal lateinit var loremIpsumFragment: LoremIpsumFragment

    @Inject
    internal lateinit var showMyAgeFragment: ShowMyAgeFragment

    private var _binding: FragmentAnalyticsBinding? = null
    private val binding get() = _binding!! // This property is only valid between onCreateView and onDestroyView.

    override fun layout() = R.layout.fragment_analytics

    override fun init() {
        binding.viewPager.adapter = SimpleFragmentPagerAdapter(childFragmentManager).apply {
            addFragments(
                showMyAgeFragment to getString(R.string.analytics_show_my_age_title),
                loremIpsumFragment to getString(R.string.analytics_lorem_ipsum_title))
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentAnalyticsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        fun newInstance() = AnalyticsRecipeFragment()
    }
}