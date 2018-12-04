package ar.com.wolox.android.cookbook.datasync

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_pokemon_search.vPokemonSearchBack

class PokemonSearchFragment : WolmoFragment<PokemonSearchPresenter>() {

    override fun layout() = R.layout.fragment_pokemon_search

    override fun init() {}

    override fun setListeners() {
        vPokemonSearchBack.setOnClickListener { requireFragmentManager().popBackStack() }
    }

    companion object {
        fun newInstance() = PokemonSearchFragment()
    }
}