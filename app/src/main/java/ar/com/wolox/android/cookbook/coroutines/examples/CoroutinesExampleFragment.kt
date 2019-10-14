package ar.com.wolox.android.cookbook.coroutines.examples

import android.view.MotionEvent
import android.view.View
import android.widget.AdapterView
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
                    x = motionEvent.rawX - movingView.width / 2
                    y = motionEvent.rawY - movingView.height / 2
                }
            }
            true
        }
    }

    override fun showOptions(options: List<CoroutinesExamplePresenter.CoroutinesExampleOption>) {
        val spinnerOptions = options.map { resources.getString(it.titleRes) }
        with(optionsSpinner) {
            adapter = ArrayAdapter(
                    requireActivity(),
                    android.R.layout.simple_spinner_item,
                    listOf(resources.getString(R.string.coroutines_examples_choose_one)) + spinnerOptions)
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                    if (pos > 0) {
                        presenter.onOptionSelected(options[pos - 1])
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
        }
    }

    override fun closeView() = requireActivity().finish()

    override fun showNumber(progress: Int) {
        progressText.text = progress.toString()
    }

    override fun showMessage(message: String) = toastFactory.show(message)

    companion object {
        fun newInstance() = CoroutinesExampleFragment()
    }
}