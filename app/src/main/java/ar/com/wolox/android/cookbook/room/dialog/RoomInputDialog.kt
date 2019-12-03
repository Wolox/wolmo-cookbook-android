package ar.com.wolox.android.cookbook.room.dialog

import android.app.AlertDialog
import android.content.Context
import android.text.InputFilter
import android.text.InputType
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import ar.com.wolox.android.cookbook.R
import javax.inject.Inject

class RoomInputDialog @Inject constructor() {

    private lateinit var input: EditText

    fun showDialog(context: Context, title: Int, listener: RoomInputDialogListener) = AlertDialog.Builder(context).apply {

        input = EditText(context).apply {
            inputType = InputType.TYPE_CLASS_TEXT
            filters = arrayOf<InputFilter>(InputFilter.LengthFilter(MAX_LENGTH))
            textSize = TEXT_SIZE
            isFocusable = true
            isFocusableInTouchMode = true
        }

        setTitle(context.getString(title))
        setView(input)

        setPositiveButton(context.getString(R.string.room_input_positive)) { dialog, _ ->
            hideSoftKeyboard(context)
            dialog?.dismiss()
            listener.onPositiveButtonClicked(input.text.toString())
        }

        setNeutralButton(context.getString(R.string.room_input_negative)) { dialog, _ ->
            hideSoftKeyboard(context)
            dialog?.dismiss()
            listener.onNegativeButtonClicked()
        }
    }

    private fun hideSoftKeyboard(context: Context) {
        val inputMethodManager: InputMethodManager =
                context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(input.windowToken, 0)
    }

    companion object {
        private const val MAX_LENGTH = 20
        private const val TEXT_SIZE = 15f
    }
}