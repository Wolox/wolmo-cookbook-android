package ar.com.wolox.android.cookbook.bounce

import androidx.recyclerview.widget.LinearLayoutManager
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.databinding.FragmentBounceBinding
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.presenter.BasePresenter

class BounceEffectFragment : WolmoFragment<FragmentBounceBinding, BasePresenter<Any>>() {

    override fun layout(): Int = R.layout.fragment_bounce

    override fun init() {
        with(binding!!) {

            verticalCarousel.apply {
                adapter = BounceAdapter(requireContext())
                edgeEffectFactory = BounceEffect()
                val cardsManager = LinearLayoutManager(requireContext())
                cardsManager.orientation = LinearLayoutManager.VERTICAL
                layoutManager = cardsManager
                isNestedScrollingEnabled = false
            }

            horizontalCarousel.apply {
                adapter = BounceAdapter(requireContext())
                edgeEffectFactory = BounceEffect(overscrollTranslation = ONE_F)
                val cardsManager = LinearLayoutManager(requireContext())
                cardsManager.orientation = LinearLayoutManager.HORIZONTAL
                layoutManager = cardsManager
                isNestedScrollingEnabled = false
            }
        }
    }

    companion object {
        var ONE_F = 1f
        fun newInstance(): BounceEffectFragment = BounceEffectFragment()
    }
}