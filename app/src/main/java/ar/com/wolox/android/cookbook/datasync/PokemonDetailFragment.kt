package ar.com.wolox.android.cookbook.datasync

import android.arch.lifecycle.GenericLifecycleObserver
import android.arch.lifecycle.Lifecycle.Event.ON_START
import android.arch.lifecycle.Lifecycle.Event.ON_STOP
import android.os.Handler
import android.os.Looper
import android.support.v4.content.ContextCompat
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
import java.util.Random

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
    }

    override fun showPokemon(pokemon: Pokemon) {
        vPokemonImageView.controller = Fresco.newDraweeControllerBuilder()
                .setDataSourceSupplier(retainingSupplier)
                .build()
        vPokemonImageView.hierarchy.roundingParams = null

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
                var spritesUrls = pokemon.sprites.values.toList()
                val random = Random()
                override fun run() {
                    retainingSupplier.replaceSupplier(Fresco.getImagePipeline().getDataSourceSupplier(
                            ImageRequest.fromUri(spritesUrls[random.nextInt(spritesUrls.size)]),
                            null,
                            ImageRequest.RequestLevel.FULL_FETCH))

                    spriteRouletteHandler.postDelayed(this, 1000L)
                }
            }

    companion object {
        fun newInstance() = PokemonDetailFragment()
    }
}