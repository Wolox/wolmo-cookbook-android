package ar.com.wolox.android.cookbook.datasync

import android.text.Editable
import android.text.TextWatcher
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_pokemon_search.vPokemonSearchBack
import kotlinx.android.synthetic.main.fragment_pokemon_search.vPokemonSearchField
import kotlinx.android.synthetic.main.fragment_pokemon_search.vPokemonSearchImageView
import java.util.Random

class PokemonSearchFragment : WolmoFragment<PokemonSearchPresenter>(), PokemonSearchView {

    override fun layout() = R.layout.fragment_pokemon_search

    override fun init() {}

    override fun setListeners() {
        vPokemonSearchBack.setOnClickListener { requireFragmentManager().popBackStack() }
        vPokemonSearchField.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                presenter.onSearchNameChanged(s.toString())
            }
        })
    }

    override fun showPokemonFound(pokemon: Pokemon) {
        val pokemonSpriteUrls = pokemon.spriteUrls
        vPokemonSearchImageView.setImageURI(pokemonSpriteUrls[Random().nextInt(pokemonSpriteUrls.size)])
    }

    override fun showNoPokemonFound() {
        vPokemonSearchImageView.setImageURI("")
    }

    companion object {
        fun newInstance() = PokemonSearchFragment()
    }
}