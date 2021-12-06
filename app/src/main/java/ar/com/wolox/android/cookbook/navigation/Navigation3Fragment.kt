package ar.com.wolox.android.cookbook.navigation

import android.os.Bundle
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.R.id.action_navigation3Fragment_to_navigation1Fragment2
import ar.com.wolox.android.cookbook.databinding.FragmentNavigation3Binding
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.presenter.BasePresenter

class Navigation3Fragment : WolmoFragment<FragmentNavigation3Binding, BasePresenter<Any>>() {

    override fun layout(): Int = R.layout.fragment_navigation3

    override fun init() {

        // Fragment3 to Fragment4 with arguments
        binding!!.fragmentNavigation3To4Button.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("test_string", binding!!.fragment3EditText.text.toString())
            requireNavController().navigate(
                R.id.action_navigation3Fragment_to_navigation4Fragment, bundle,
                createOptionsWithDefaultAnimations()
            )
        }
    }

    override fun onBackPressed(): Boolean {
        // Going back to Navigation1Fragment and popup backstack
        requireNavController().navigate(
            action_navigation3Fragment_to_navigation1Fragment2, null,
            getBackwardOptions(R.id.navigation1Fragment, true)
        )
        return true
    }
}
