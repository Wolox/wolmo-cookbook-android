package ar.com.wolox.android.cookbook.navigation

import androidx.navigation.Navigation
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import kotlinx.android.synthetic.main.fragment_navigation2a.fragment_navigation2a_to3_button

class Navigation2AFragment : WolmoFragment<BasePresenter<Any>>() {

    override fun layout(): Int = R.layout.fragment_navigation2a

    override fun init() {
        fragment_navigation2a_to3_button.setOnClickListener {
            Navigation.findNavController(view!!).navigate(R.id.action_navigation2AFragment_to_navigation3Fragment)
        }
    }
}
