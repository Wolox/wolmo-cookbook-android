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

    fun showDialog(context: Context, listener: RoomInputDialogListener) = AlertDialog.Builder(context).apply {

        this.setTitle(context.getString(R.string.room_input_title))

        input = EditText(context)
        input.inputType = InputType.TYPE_CLASS_TEXT
        input.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(20))
        input.textSize = 14f
        input.isFocusable = true
        input.isFocusableInTouchMode = true
        this.setView(input)

        this.setPositiveButton(context.getString(R.string.room_input_positive)) { dialog, _ ->
            hideSoftKeyboard(context)
            dialog?.dismiss()
            listener.onPositiveButtonClicked(input.text.toString())
        }

        this.setNegativeButton(context.getString(R.string.room_input_negative)) { dialog, _ ->
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
}