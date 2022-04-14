package ar.com.wolox.android.cookbook.bounce

import androidx.recyclerview.widget.LinearLayoutManager
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.databinding.FragmentBounceBinding
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.presenter.BasePresenter

class BounceEffectFragment : WolmoFragment<FragmentBounceBinding, BasePresenter<Any>>() {

    override fun init() {
        with(binding!!) {
            horizontalCarousel.apply {
                adapter = BouncingAdapter(requireContext())
                edgeEffectFactory = BounceEffect(BounceOrientation.HORIZONTAL)
                val cardsManager = LinearLayoutManager(requireContext())
                cardsManager.orientation = LinearLayoutManager.HORIZONTAL
                layoutManager = cardsManager
                isNestedScrollingEnabled = false
            }

            verticalCarousel.apply {
                adapter = BouncingAdapter(requireContext())
                edgeEffectFactory = BounceEffect(BounceOrientation.VERTICAL)
                val cardsManager = LinearLayoutManager(requireContext())
                cardsManager.orientation = LinearLayoutManager.VERTICAL
                layoutManager = cardsManager
                isNestedScrollingEnabled = false
            }
        }
    }

    override fun layout(): Int = R.layout.fragment_bounce

    companion object {
        fun newInstance(): BounceEffectFragment = BounceEffectFragment()
    }
}