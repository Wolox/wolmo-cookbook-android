package ar.com.wolox.android.cookbook.datasync

import android.arch.lifecycle.GenericLifecycleObserver
import android.arch.lifecycle.Lifecycle.Event.ON_START
import android.arch.lifecycle.Lifecycle.Event.ON_STOP
import android.os.Handler
import android.os.Looper
import android.support.v4.content.ContextCompat
import android.view.View
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import com.facebook.common.references.CloseableReference
import com.facebook.datasource.RetainingDataSourceSupplier
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.generic.RoundingParams
import com.facebook.imagepipeline.image.CloseableImage
import com.facebook.imagepipeline.request.ImageRequest
import com.facebook.imagepipeline.request.ImageRequestBuilder
import kotlinx.android.synthetic.main.fragment_pokemon_detail.vPokemonImageView
import kotlinx.android.synthetic.main.fragment_pokemon_detail.vPokemonName
import kotlinx.android.synthetic.main.fragment_pokemon_detail.vPokemonType1
import kotlinx.android.synthetic.main.fragment_pokemon_detail.vPokemonType2
import kotlinx.android.synthetic.main.fragment_pokemon_detail.vPokemonTypeContainer
import java.util.LinkedList

class PokemonDetailFragment : WolmoFragment<PokemonDetailPresenter>(), PokemonDetailView {

    private lateinit var retainingSupplier: RetainingDataSourceSupplier<CloseableReference<CloseableImage>>
    private val spriteRouletteHandler: Handler = Handler(Looper.getMainLooper())

    override fun layout() = R.layout.fragment_pokemon_detail

    override fun init() {
        retainingSupplier = RetainingDataSourceSupplier()
    }

    override fun showNoPokemon() {
        vPokemonImageView.controller = Fresco.newDraweeControllerBuilder()
                .setLowResImageRequest(ImageRequestBuilder.newBuilderWithResourceId(R.drawable.bg_pokemon_roulette).build())
                .setAutoPlayAnimations(true)
                .build()
        vPokemonImageView.hierarchy.roundingParams = RoundingParams.fromCornersRadius(resources.getDimension(R.dimen.spacing_large))
                .setRoundingMethod(RoundingParams.RoundingMethod.OVERLAY_COLOR)
                .setOverlayColor(ContextCompat.getColor(requireContext(), android.R.color.white))

        vPokemonName.setText(R.string.data_sync_no_pokemon)
        vPokemonTypeContainer.visibility = View.INVISIBLE
        vPokemonType1.text = ""
        vPokemonType2.text = ""
    }

    override fun showPokemon(pokemon: Pokemon) {
        vPokemonImageView.controller = Fresco.newDraweeControllerBuilder()
                .setDataSourceSupplier(retainingSupplier)
                .build()
        vPokemonImageView.hierarchy.roundingParams = null
        vPokemonName.text = pokemon.name.capitalize()
        vPokemonType1.text = pokemon.firstType.name.capitalize()
        vPokemonType2.visibility = if (pokemon.secondType != null) View.VISIBLE else View.GONE
        vPokemonType2.text = pokemon.secondType?.name?.capitalize()
        vPokemonTypeContainer.visibility = View.VISIBLE

        lifecycle.addObserver(GenericLifecycleObserver { _, event ->
            @Suppress("NON_EXHAUSTIVE_WHEN")
            when (event) {
                ON_START -> {
                    spriteRouletteHandler.postDelayed(generateSpriteRouletteTask(pokemon), 1000L)
                }
                ON_STOP -> {
                    spriteRouletteHandler.removeCallbacksAndMessages(null)
                }
            }
        })
    }

    private fun generateSpriteRouletteTask(pokemon: Pokemon): Runnable =
            object : Runnable {
                var spritesUrls = LinkedList<String>()
                override fun run() {
                    if (spritesUrls.isEmpty()) spritesUrls.addAll(pokemon.spriteUrls.shuffled())

                    retainingSupplier.replaceSupplier(Fresco.getImagePipeline().getDataSourceSupplier(
                            ImageRequest.fromUri(spritesUrls.pop()),
                            null,
                            ImageRequest.RequestLevel.FULL_FETCH))
                    spriteRouletteHandler.postDelayed(this, 1000L)
                }
            }

    companion object {
        fun newInstance() = PokemonDetailFragment()
    }
}