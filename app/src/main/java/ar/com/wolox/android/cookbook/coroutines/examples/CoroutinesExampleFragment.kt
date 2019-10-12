package ar.com.wolox.android.cookbook.coroutines.examples

import android.view.MotionEvent
import android.widget.ArrayAdapter
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.util.ToastFactory
import kotlinx.android.synthetic.main.fragment_coroutines_example.*
import javax.inject.Inject

class CoroutinesExampleFragment : WolmoFragment<CoroutinesExamplePresenter>(), CoroutinesExampleView {

    @Inject
    internal lateinit var toastFactory: ToastFactory

    override fun layout() = R.layout.fragment_coroutines_example

    override fun init() {
        container.setOnTouchListener { _, motionEvent ->
            if (motionEvent.actionMasked == MotionEvent.ACTION_MOVE || motionEvent.actionMasked == MotionEvent.ACTION_DOWN) {
                with(movingView) {
                    x = motionEvent.rawX
                    y = motionEvent.rawY
                }
            }
            true
        }
    }

    override fun showOptions(options: List<CoroutinesExamplePresenter.CoroutinesExampleOption>) {
        optionsSpinner.adapter = ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, options)
    }

    override fun closeView() = requireActivity().finish()

    override fun showNumber(progress: Int) {
        progressText.text = progress.toString()
    }

    companion object {
        fun newInstance() = CoroutinesExampleFragment()
    }
}