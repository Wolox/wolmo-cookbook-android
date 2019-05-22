package ar.com.wolox.android.cookbook.motionLayout

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_motion_layout_menu.*

class MotionMenuFragment : WolmoFragment<MotionPresenter>(), IMotionView {

    override fun layout(): Int = R.layout.fragment_motion_layout_menu

    override fun init() {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
        //
        //
        //
    }

    fun setUpListeners() {
        bViewPagerMotion.setOnClickListener {
        }
    }
}