package ar.com.wolox.android.cookbook.animatedinput

import android.content.Context
import android.content.res.TypedArray
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.constraintlayout.widget.ConstraintLayout
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.databinding.AnimatedEditTextBinding

/*
*   This component is an example of how an animation could be implemented within a textedit.
*   The problem with animating the content of the EditText is that it is not a component itself.
*   The proposed workaround is to overlap a TextView over an EditText with transparent text.
*   Many more features can be implemented on this component (pass it through attributes, for example
*   the color of the text selector, or the underline). I left it as minimal as possible so that it can be
*   extend or use at will.
*/

class AnimatedEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = AnimatedEditTextBinding.inflate(LayoutInflater.from(context), this, true)

    private lateinit var textChangeAnimation: Animation
    private var dotSeparator: CurrencySeparator = CurrencySeparator.DOT

    var onInputChanged: (String) -> Unit = {}

    private val watcher = object : TextWatcher {
        override fun beforeTextChanged(content: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(content: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(content: Editable?) {
            binding.textContainer.apply {
                text = getCurrentText()
                startAnimation(textChangeAnimation)
            }
            onInputChanged.invoke(getCurrentText())
        }
    }

    init {
        with(binding) {
            val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.AnimatedEditText, 0, 0)
            val defaultText = typedArray.getString(R.styleable.AnimatedEditText_defaultText) ?: INITIAL_PRIMARY_VALUE
            val animationId = typedArray.getResourceId(R.styleable.AnimatedEditText_onChangeAnimation, 0)
            dotSeparator = CurrencySeparator.values()[typedArray.getInt(R.styleable.AnimatedEditText_dotSeparator, 0)]
            textChangeAnimation = AnimationUtils.loadAnimation(context, animationId)
            primaryValue.addTextChangedListener(watcher)
            typedArray.recycle()
        }
    }

    fun getCurrentText(): String {
        return binding.primaryValue.text.toString().replace(DOT, if (dotSeparator == CurrencySeparator.DOT) DOT else COMMA)
    }

    companion object {
        private const val INITIAL_PRIMARY_VALUE = "0.00"
        private const val COMMA = ","
        private const val DOT = "."
    }
}

private enum class CurrencySeparator {
    DOT,
    COMMA
}
