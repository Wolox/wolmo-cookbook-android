package ar.com.wolox.android.cookbook.camerax

import android.view.View
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.activity.WolmoActivity
import kotlinx.android.synthetic.main.activity_base.*

class CameraXRecipeActivity : WolmoActivity() {

    val fragment = CameraXRecipeFragment()

    override fun layout(): Int = R.layout.activity_base

    override fun init() = replaceFragment(R.id.vActivityBaseContent, fragment)

    override fun onResume() {
        vActivityBaseContent.postDelayed({
            vActivityBaseContent.systemUiVisibility = FLAGS_FULLSCREEN
        }, IMMERSIVE_FLAG_TIMEOUT)
        super.onResume()
    }

    companion object {
        private const val FLAGS_FULLSCREEN =
                View.SYSTEM_UI_FLAG_LOW_PROFILE or
                        View.SYSTEM_UI_FLAG_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        private const val IMMERSIVE_FLAG_TIMEOUT = 500L
    }
}