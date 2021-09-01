package ar.com.wolox.android.cookbook.lottie

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import kotlinx.android.synthetic.main.fragment_lottie.*

/** Lottie Library:
 * Attributes to use in component LottieAnimationView in xml file:
 *
 * lottie_loop - A boolean to ensure that the animation stays in a loop.
 * lottie_speed - This determines the speed of the animation.
 * lottie_autoplay - A boolean that starts the animation.
 * lottie_url - The JSON URL from the website.
 * lottie_rawRes - This contains the bundled animation on your app.
 * lottie_fileName - If you have added the JSON files to the assets folder, use this instead of lottie_rawRes.
 *
 * Resources:
 * - Download the required JSON files from https://lottiefiles.com/
 * - Save them to the assets folder of the project
 **/

class LottieRecipeFragment : WolmoFragment<BasePresenter<Any>>(), LottieRecipeView {
    lateinit var lottieRecipeAdapter: LottieRecipeAdapter

    override fun init() {
        lottieRecipeAdapter = LottieRecipeAdapter(listOf(
                R.raw.lottie_robot,
                R.raw.lottie_notekook,
                R.raw.lottie_developer,
                R.raw.lottie_excercise,
                R.raw.lottie_metiorite

        ))
        viewPagerLottie.adapter = lottieRecipeAdapter
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun setListeners() {
        viewPagerLottie.setOnScrollChangeListener { _, _, _, _, _ ->
            onPagerScroll(viewPagerLottie.currentItem)
        }
    }

    private fun onPagerScroll(position: Int) {
        leftArrow.togglePresence(position > POSITION_ZERO && position <= lottieRecipeAdapter.count - ONE)
        rightArrow.togglePresence(position >= POSITION_ZERO && position < lottieRecipeAdapter.count - ONE)
    }

    private fun View.togglePresence(present: Boolean) {
        visibility = if (present) View.VISIBLE else View.GONE
    }

    override fun layout(): Int = R.layout.fragment_lottie

    companion object {
        const val POSITION_ZERO = 0
        const val ONE = 1

        fun newInstance(): LottieRecipeFragment = LottieRecipeFragment()
    }
}