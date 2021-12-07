package ar.com.wolox.android.cookbook.navigation

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.databinding.FragmentNavigation2Binding
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.presenter.BasePresenter

class Navigation2Fragment : WolmoFragment<FragmentNavigation2Binding, BasePresenter<Any>>() {

    override fun layout(): Int = R.layout.fragment_navigation2

    override fun init() {
        // Going back programatically
        binding!!.fragmentNavigation2To1Button.setOnClickListener {
            requireNavController().popBackStack()
        }
        // Fragment2B to Fragment3 / When clicking back on Fragment3 it will return to Fragment1
        binding!!.fragmentNavigation2To3Button.setOnClickListener {
            requireNavController().navigate(R.id.action_navigation2Fragment_to_navigation3Fragment, null,
                    createOptionsWithDefaultAnimations())
        }
    }
}
