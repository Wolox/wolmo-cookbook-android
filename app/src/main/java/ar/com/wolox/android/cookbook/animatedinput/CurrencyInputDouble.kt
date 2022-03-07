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
import com.example.inputtypes.R
import com.example.inputtypes.databinding.CurrencyInputDoubleBinding

/*
*  Este componente es un ejemplo de como se podria implementar una animacion dentro de un textedit.
*  El problema de animar el contenido de EditText es que no es un componente en si.
*  El workaround que se propone es solapar un TextView sobre un EditText con texto transparente.
*  Sobre este componente se pueden implementar muchas mas features (pasarle por atributos, por ejemplo
*  el color del selector de texto, o el underline). Lo deje lo mas minimal posible para que se pueda
*  extender o utilizar a gusto. 
*/

class CurrencyInputDouble @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = CurrencyInputDoubleBinding.inflate(LayoutInflater.from(context), this, true)

    private lateinit var textChangeAnimation : Animation
    private var dotSeparator : CurrencySeparator = CurrencySeparator.DOT

    var onInputChanged : (String) -> Unit = {}

    private val watcher = object : TextWatcher{
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
        with(binding){
            val typedArray : TypedArray = context.obtainStyledAttributes(attrs, R.styleable.CurrencyInputDouble, 0,0)
            val defaultText = typedArray.getString(R.styleable.CurrencyInputDouble_defaultText) ?: INITIAL_PRIMARY_VALUE
            val animationId = typedArray.getResourceId(R.styleable.CurrencyInputDouble_onChangeAnimation, 0)
            dotSeparator = CurrencySeparator.values()[typedArray.getInt(R.styleable.CurrencyInputDouble_dotSeparator,0)]
            textChangeAnimation = AnimationUtils.loadAnimation(context, animationId)
            primaryValue.addTextChangedListener(watcher)
            typedArray.recycle()
        }
    }

    fun getCurrentText() : String {
        return binding.primaryValue.text.toString().replace(DOT, if(dotSeparator == CurrencySeparator.DOT) DOT else COMMA)
    }

    companion object {
        private const val INITIAL_PRIMARY_VALUE = "0.00"
        private const val COMMA = ","
        private const val DOT = "."
    }

}

enum class CurrencySeparator {DOT, COMMA}
