package ar.com.wolox.android.cookbook.navigation

import androidx.navigation.Navigation
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import kotlinx.android.synthetic.main.fragment_navigation2b.*

class Navigation2BFragment : WolmoFragment<BasePresenter<Any>>() {

    override fun layout(): Int = R.layout.fragment_navigation2b

    override fun init() {
        fragment_navigation2b_to3_button.setOnClickListener {
            Navigation.findNavController(view!!).navigate(R.id.action_navigation2BFragment_to_navigation3Fragment)
        }
    }
}
