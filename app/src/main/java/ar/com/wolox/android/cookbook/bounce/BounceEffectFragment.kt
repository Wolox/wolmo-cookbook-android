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
                edgeEffectFactory = BounceEffect(BounceOrientation.VERTICAL)
                val cardsManager = LinearLayoutManager(requireContext())
                cardsManager.orientation = LinearLayoutManager.VERTICAL
                layoutManager = cardsManager
                isNestedScrollingEnabled = false
            }

            horizontalCarousel.apply {
                adapter = BounceAdapter(requireContext())
                edgeEffectFactory = BounceEffect(BounceOrientation.HORIZONTAL, overscrollTranslation = 1f)
                val cardsManager = LinearLayoutManager(requireContext())
                cardsManager.orientation = LinearLayoutManager.HORIZONTAL
                layoutManager = cardsManager
                isNestedScrollingEnabled = false
            }
        }
    }

    companion object {
        fun newInstance(): BounceEffectFragment = BounceEffectFragment()
    }
}