package ar.com.wolox.android.cookbook.koin.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import org.koin.android.ext.android.inject

abstract class BaseFragment<out T : BasePresenter<*>> : Fragment() {

    protected val toastFactory: ToastFactory by inject()

    abstract val presenter: T

    abstract val layout: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(layout, container, false)

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    abstract fun init()
}
