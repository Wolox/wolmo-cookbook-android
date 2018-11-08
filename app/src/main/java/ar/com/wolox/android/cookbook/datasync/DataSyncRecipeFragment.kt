package ar.com.wolox.android.cookbook.datasync

import android.arch.lifecycle.GenericLifecycleObserver
import android.arch.lifecycle.Lifecycle.Event.ON_START
import android.arch.lifecycle.Lifecycle.Event.ON_STOP
import android.os.Handler
import android.os.Looper
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.di.scopes.ActivityScope
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.util.ToastFactory
import com.facebook.common.references.CloseableReference
import com.facebook.datasource.RetainingDataSourceSupplier
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.image.CloseableImage
import com.facebook.imagepipeline.request.ImageRequest
import kotlinx.android.synthetic.main.fragment_data_sync_recipe.vPokemonImageView
import java.util.Random
import javax.inject.Inject

@ActivityScope
class DataSyncRecipeFragment : WolmoFragment<DataSyncRecipePresenter>(), DataSyncRecipeView {

    @Inject lateinit var toastFactory: ToastFactory

    private lateinit var retainingSupplier: RetainingDataSourceSupplier<CloseableReference<CloseableImage>>
    private val spriteRouletteHandler: Handler = Handler(Looper.getMainLooper())

    override fun layout() = R.layout.fragment_data_sync_recipe

    override fun init() {
        retainingSupplier = RetainingDataSourceSupplier()
        vPokemonImageView.controller = Fresco.newDraweeControllerBuilder()
                .setDataSourceSupplier(retainingSupplier)
                .build()
    }

    override fun showPokemon(pokemon: Pokemon) {
        toastFactory.show("Pokemon ${pokemon.name} is being shown!")

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

    override fun showError() {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        fun newInstance() = DataSyncRecipeFragment()
    }
}