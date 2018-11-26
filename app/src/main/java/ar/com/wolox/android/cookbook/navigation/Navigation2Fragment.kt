package ar.com.wolox.android.cookbook.navigation

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import kotlinx.android.synthetic.main.fragment_navigation2.*

class Navigation2Fragment : WolmoFragment<BasePresenter<Any>>() {

    override fun layout(): Int = R.layout.fragment_navigation2

    override fun init() {

        // Going back programatically
        fragment_navigation2_to1_button.setOnClickListener {
            requireNavController().popBackStack()
        }

        // Fragment2B to Fragment3 / When clicking back on Fragment3 it will return to Fragment1
        fragment_navigation2_to3_button.setOnClickListener {
            requireNavController().navigate(R.id.action_navigation2Fragment_to_navigation3Fragment, null,
                    createOptionsWithDefaultAnimations())
        }
    }
}
