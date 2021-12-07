package ar.com.wolox.android.cookbook.navigation

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.databinding.FragmentNavigation4Binding
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.presenter.BasePresenter

class Navigation4Fragment : WolmoFragment<FragmentNavigation4Binding, BasePresenter<Any>>() {

    override fun layout(): Int = R.layout.fragment_navigation4

    override fun init() {
        // Setting arguments from Fragment3
        binding!!.fragment4TextView.setText(arguments?.getString("test_string").toString())
    }

    override fun onBackPressed(): Boolean {
        // Finish activity when pressing back button
        requireActivity().finish()
        return true
    }
}
