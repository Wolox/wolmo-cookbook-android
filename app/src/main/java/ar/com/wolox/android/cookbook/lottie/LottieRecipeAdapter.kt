package ar.com.wolox.android.cookbook.lottie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import ar.com.wolox.android.cookbook.R
import kotlinx.android.synthetic.main.item_lottie.view.*

class LottieRecipeAdapter(
    private val item: List<Int>
) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context).inflate(R.layout.item_lottie, null)
        return view.apply {
            val icon = item[position]
            vItemLottieImage.apply {
                setAnimation(icon)
                playAnimation()
                loop(true)
            }
            container.addView(this)
        }
    }

    override fun destroyItem(container: ViewGroup, position: Int, viewObject: Any) {
        container.removeView(viewObject as View)
    }

    override fun getCount() = item.size

    override fun isViewFromObject(view: View, viewObject: Any) = view === viewObject
}