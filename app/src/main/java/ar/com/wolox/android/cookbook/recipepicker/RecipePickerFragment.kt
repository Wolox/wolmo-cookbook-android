package ar.com.wolox.android.cookbook.recipepicker

import android.os.Handler
import android.os.Looper
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.util.ToastFactory
import javax.inject.Inject

class RecipePickerFragment : WolmoFragment<RecipePickerPresenter>() {

    @Inject lateinit var toastFactory: ToastFactory

    override fun layout() = R.layout.fragment_base

    override fun init() {
        Handler(Looper.getMainLooper()).postDelayed({
                    toastFactory.show("Welcome to Wolmo Android Cookbook!")
                },
                1500)
    }

    companion object {

        fun newInstance() = RecipePickerFragment()
    }
}