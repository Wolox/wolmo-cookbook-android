package ar.com.wolox.android.cookbook.navigation

import ar.com.wolox.android.cookbook.CookbookApplication
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.navigation.screens.Navigation4Screen
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import kotlinx.android.synthetic.main.fragment_navigation4.*
import me.aartikov.alligator.annotations.RegisterScreen

@RegisterScreen(Navigation4Screen::class)
class Navigation4Fragment : WolmoFragment<BasePresenter<Any>>() {

    private val screenResolver = CookbookApplication.getScreenResolver()

    override fun layout(): Int = R.layout.fragment_navigation4

    override fun init() {
        val screen = screenResolver.getScreen<Navigation4Screen>(this)
        fragment4_text_view.text = screen.getMessage()
    }

    override fun onBackPressed(): Boolean {
        // Finish activity when pressing back button
        activity!!.finish()
        return true
    }
}