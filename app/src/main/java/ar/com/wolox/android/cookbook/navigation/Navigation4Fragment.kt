package ar.com.wolox.android.cookbook.navigation

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import kotlinx.android.synthetic.main.fragment_navigation4.*

class Navigation4Fragment : WolmoFragment<BasePresenter<Any>>() {

    override fun layout(): Int = R.layout.fragment_navigation4

    override fun init() {
        // Setting arguments from Fragment3
        fragment4_text_view.setText(arguments?.getString("test_string").toString())
    }

    override fun onBackPressed(): Boolean {
        // Finish activity when pressing back button
        activity!!.finish()
        return true
    }
}