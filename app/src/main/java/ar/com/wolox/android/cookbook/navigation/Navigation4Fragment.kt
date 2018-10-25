package ar.com.wolox.android.cookbook.navigation

import androidx.navigation.Navigation
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import kotlinx.android.synthetic.main.fragment_navigation4.fragment4_text_view
import kotlinx.android.synthetic.main.fragment_navigation4.fragment_navigation4_newActivity

class Navigation4Fragment : WolmoFragment<BasePresenter<Any>>() {

    override fun layout(): Int = R.layout.fragment_navigation4

    override fun init() {
        fragment4_text_view.setText(arguments?.getString("test_string").toString())
        fragment_navigation4_newActivity.setOnClickListener {
            Navigation.findNavController(view!!).navigate(R.id.action_navigation4Fragment_to_newNavigationFragment)
        }
    }
}