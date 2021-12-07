package ar.com.wolox.android.cookbook.navigation

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.databinding.FragmentNavigation1Binding
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.presenter.BasePresenter

class Navigation1Fragment : WolmoFragment<FragmentNavigation1Binding, BasePresenter<Any>>() {

    override fun layout(): Int = R.layout.fragment_navigation1

    override fun init() {

        // Fragment1 to Fragment2
        binding!!.fragmentNavigation1To2Button.setOnClickListener {
            requireNavController().navigate(R.id.action_navigation1Fragment_to_navigation2BFragment, null,
                    createOptionsWithDefaultAnimations())
        }

        // Fragment1 to NewActivity
        binding!!.fragmentNavigation1ToNewActivity.setOnClickListener {
            requireNavController().navigate(R.id.action_navigation1Fragment_to_newNavigationFragment, null,
                    createOptionsWithDefaultAnimations())
        }
    }
}
