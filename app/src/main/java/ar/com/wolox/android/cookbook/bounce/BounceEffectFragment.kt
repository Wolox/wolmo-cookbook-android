package ar.com.wolox.android.cookbook.bounce

import androidx.recyclerview.widget.LinearLayoutManager
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.databinding.FragmentBounceBinding
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import kotlinx.android.synthetic.main.fragment_bounce.*

class BounceEffectFragment
    : WolmoFragment<FragmentBounceBinding, BasePresenter<Any>>() {

    override fun init() {
        horizontalCarousel.apply {
            adapter = BouncingAdapter(requireContext())
            edgeEffectFactory = BouncingEffect(BouncingOrientation.HORIZONTAL)
            val cardsManager = LinearLayoutManager(requireContext())
            cardsManager.orientation = LinearLayoutManager.HORIZONTAL
            layoutManager = cardsManager
            isNestedScrollingEnabled = false
        }

        verticalCarousel.apply {
            adapter = BouncingAdapter(requireContext())
            edgeEffectFactory = BouncingEffect(BouncingOrientation.VERTICAL)
            val cardsManager = LinearLayoutManager(requireContext())
            cardsManager.orientation = LinearLayoutManager.VERTICAL
            layoutManager = cardsManager
            isNestedScrollingEnabled = false
        }
    }

    override fun layout(): Int = R.layout.fragment_bounce

    companion object {
        fun newInstance(): BounceEffectFragment = BounceEffectFragment()
    }
}