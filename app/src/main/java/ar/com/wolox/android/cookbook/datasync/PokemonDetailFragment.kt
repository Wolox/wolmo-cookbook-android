package ar.com.wolox.android.cookbook.datasync

import androidx.lifecycle.GenericLifecycleObserver
import androidx.lifecycle.Lifecycle.Event.ON_START
import androidx.lifecycle.Lifecycle.Event.ON_STOP
import androidx.lifecycle.LifecycleObserver
import android.os.Handler
import android.os.Looper
import androidx.core.content.ContextCompat
import android.view.View
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.databinding.FragmentPokemonDetailBinding
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import com.facebook.common.references.CloseableReference
import com.facebook.datasource.RetainingDataSourceSupplier
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.generic.RoundingParams
import com.facebook.imagepipeline.image.CloseableImage
import com.facebook.imagepipeline.request.ImageRequest
import com.facebook.imagepipeline.request.ImageRequestBuilder
import kotlinx.android.synthetic.main.fragment_pokemon_detail.vPokemonDetailImageView
import kotlinx.android.synthetic.main.fragment_pokemon_detail.vPokemonDetailName
import kotlinx.android.synthetic.main.fragment_pokemon_detail.vPokemonDetailSearchButton
import kotlinx.android.synthetic.main.fragment_pokemon_detail.vPokemonDetailType1
import kotlinx.android.synthetic.main.fragment_pokemon_detail.vPokemonDetailType2
import kotlinx.android.synthetic.main.fragment_pokemon_detail.vPokemonDetailTypeContainer
import java.util.LinkedList

class PokemonDetailFragment : WolmoFragment<FragmentPokemonDetailBinding, PokemonDetailPresenter>(),
    PokemonDetailView {

    private lateinit var retainingSupplier: RetainingDataSourceSupplier<CloseableReference<CloseableImage>>
    private val spriteRouletteHandler = Handler(Looper.getMainLooper())
    private var lastRouletteObserver: LifecycleObserver? = null
        set(value) {
            spriteRouletteHandler.removeCallbacksAndMessages(null)
            field?.let { lifecycle.removeObserver(it) }
            field = value
            field?.let { lifecycle.addObserver(it) }
        }

    override fun layout() = R.layout.fragment_pokemon_detail

    override fun init() {
        retainingSupplier = RetainingDataSourceSupplier()
    }

    override fun setListeners() {
        vPokemonDetailSearchButton.setOnClickListener {
            requireFragmentManager().beginTransaction()
                .addToBackStack(null)
                .setCustomAnimations(
                    R.anim.slide_in_right, R.anim.slide_out_left,
                    R.anim.slide_in_left, R.anim.slide_out_right
                )
                .add(R.id.vActivityBaseContent, PokemonSearchFragment.newInstance())
                .hide(this)
                .commit()
        }
    }

    override fun showNoPokemon() {
        vPokemonDetailImageView.controller = Fresco.newDraweeControllerBuilder()
            .setLowResImageRequest(
                ImageRequestBuilder.newBuilderWithResourceId(R.drawable.bg_pokemon_roulette).build()
            )
            .setAutoPlayAnimations(true)
            .build()
        vPokemonDetailImageView.hierarchy.roundingParams =
            RoundingParams.fromCornersRadius(resources.getDimension(R.dimen.spacing_large))
                .setRoundingMethod(RoundingParams.RoundingMethod.OVERLAY_COLOR)
                .setOverlayColor(ContextCompat.getColor(requireContext(), android.R.color.white))

        vPokemonDetailName.setText(R.string.data_sync_no_pokemon)
        vPokemonDetailTypeContainer.visibility = View.INVISIBLE
        vPokemonDetailType1.text = ""
        vPokemonDetailType2.text = ""
    }

    override fun showPokemonDetail(pokemon: Pokemon) {
        vPokemonDetailImageView.controller = Fresco.newDraweeControllerBuilder()
            .setDataSourceSupplier(retainingSupplier)
            .build()
        vPokemonDetailImageView.hierarchy.roundingParams = null
        vPokemonDetailName.text = pokemon.name.capitalize()
        vPokemonDetailType1.text = pokemon.firstType.name.capitalize()
        vPokemonDetailType2.visibility = if (pokemon.secondType != null) View.VISIBLE else View.GONE
        vPokemonDetailType2.text = pokemon.secondType?.name?.capitalize()
        vPokemonDetailTypeContainer.visibility = View.VISIBLE

        lastRouletteObserver = GenericLifecycleObserver { _, event ->
            @Suppress("NON_EXHAUSTIVE_WHEN")
            when (event) {
                ON_START -> {
                    spriteRouletteHandler.postDelayed(generateSpriteRouletteTask(pokemon), 1000L)
                }
                ON_STOP -> {
                    spriteRouletteHandler.removeCallbacksAndMessages(null)
                }
            }
        }
    }

    private fun generateSpriteRouletteTask(pokemon: Pokemon): Runnable =
        object : Runnable {
            var spritesUrls = LinkedList<String>()
            override fun run() {
                if (spritesUrls.isEmpty()) spritesUrls.addAll(pokemon.spriteUrls.shuffled())

                retainingSupplier.replaceSupplier(
                    Fresco.getImagePipeline().getDataSourceSupplier(
                        ImageRequest.fromUri(spritesUrls.pop()),
                        null,
                        ImageRequest.RequestLevel.FULL_FETCH
                    )
                )
                spriteRouletteHandler.postDelayed(this, 1000L)
            }
        }

    companion object {
        fun newInstance() = PokemonDetailFragment()
    }
}