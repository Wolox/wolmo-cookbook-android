package ar.com.wolox.android.cookbook.coroutines.core

import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.coroutines.examples.CoroutinesExampleView
import ar.com.wolox.android.cookbook.coroutines.examples.LogType
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import ar.com.wolox.wolmo.core.util.ToastFactory
import kotlinx.android.synthetic.main.fragment_coroutines_base_example.*
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

abstract class CoroutinesRecipeItemFragment<T : BasePresenter<*>> : WolmoFragment<T>(), CoroutinesExampleView {

    @Inject
    internal lateinit var toastFactory: ToastFactory

    private var separationBetweenLogs = 0

    @LayoutRes
    open val childrenLayout: Int? = null

    @StringRes
    open val titleRes: Int? = null

    @StringRes
    open val descriptionRes: Int? = null

    @CallSuper
    override fun layout() = R.layout.fragment_coroutines_base_example

    @CallSuper
    override fun init() {
        titleRes?.let { title.text = resources.getString(it) }
        descriptionRes?.let { description.text = resources.getString(it) }
        childrenLayout?.let { View.inflate(context, it, container) }
    }

    override fun log(type: LogType, message: String) {
        progressText.addView(TextView(context).apply {
            text = "%s %s/...: %s".format(
                    SimpleDateFormat("HH:mm:ss", Locale.US).format(Calendar.getInstance().time),
                    type.text,
                    message)
        }, LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT).apply { topMargin = separationBetweenLogs })

        progressText.post {
            scrollView.fullScroll(View.FOCUS_DOWN)
        }
    }
}