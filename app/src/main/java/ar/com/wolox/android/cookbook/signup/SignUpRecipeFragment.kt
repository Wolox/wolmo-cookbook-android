package ar.com.wolox.android.cookbook.signup

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import android.app.DatePickerDialog
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.WindowManager
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.fragment_sign_up.*
import java.util.Calendar
import java.util.Date

class SignUpRecipeFragment : WolmoFragment<SignUpRecipePresenter>(), SignUpView {

    override fun layout(): Int = R.layout.fragment_sign_up

    private lateinit var calendar: Calendar
    private var datePicker: DatePickerDialog? = null
    override fun init() {
        vDataFirstName.addTextChangedListener(TextDataWatcher(this))
        vDataLastName.addTextChangedListener(TextDataWatcher(this))
        vDataEmail.addTextChangedListener(TextDataWatcher(this))
        vDataPass.addTextChangedListener(TextDataWatcher(this))
        vDataPassConfirm.addTextChangedListener(TextDataWatcher(this))
        vDataBornDate.addTextChangedListener(TextDataWatcher(this))
        calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        datePicker =
                activity?.let {
                    DatePickerDialog(
                            it,
                            DatePickerDialog.OnDateSetListener { _, yearData, monthOfYear, dayOfMonth ->
                                vDataBornDate.setText(
                                        getString(
                                                R.string.sign_up_born_date_data,
                                                dayOfMonth,
                                                monthOfYear,
                                                yearData
                                        )
                                )
                            },
                            year,
                            month,
                            day
                    )
                }
        datePicker?.datePicker?.maxDate = Date().time
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }

    override fun setListeners() {
        vDataBornDate.setOnClickListener {
            datePicker?.show()
        }
        vSignUpBtn.setOnClickListener {
            presenter.onSignUpButtonClicked()
        }
    }

    private fun validateFormTextWatcher() {
        vSignUpBtn.isEnabled = (vDataFirstName.text!!.isNotEmpty() &&
                vDataLastName.text!!.isNotEmpty() && vDataEmail.text!!.isNotEmpty() &&
                vDataPass.text!!.isNotEmpty() && vDataPassConfirm.text!!.isNotEmpty() &&
                vDataBornDate.text!!.isNotEmpty())
    }

    override fun validateForm(): Boolean =
            validField(vWrapFirstName, vDataFirstName, R.string.sign_up_first_name) &&
                    validField(vWrapLastName, vDataLastName, R.string.sign_up_last_name) &&
                    validField(vWrapBornDate, vDataBornDate, R.string.sign_up_born_date_label) &&
                    validateEmail()

    private fun validField(
        viewContainer: TextInputLayout,
        view: TextInputEditText,
        field: Int
    ): Boolean =
            if (view.text.isNullOrEmpty()) {
                viewContainer.run {
                    error = getString(R.string.sign_up_error, getString(field))
                    isErrorEnabled = true
                }
                false
            } else {
                viewContainer.isErrorEnabled = false
                true
            }

    private fun validateEmail(): Boolean = if (vDataEmail.text.toString().isNotEmpty() &&
            Patterns.EMAIL_ADDRESS.matcher(vDataEmail.text.toString()).matches()
    ) {
        vWrapEmail.isErrorEnabled = false
        true
    } else {
        vWrapEmail.run {
            error = getString(R.string.sign_up_error, getString(R.string.sign_up_born_date_label))
            isErrorEnabled = true
        }
        false
    }

    override fun toggleSignUpResult(result: Boolean) {
        Toast.makeText(
                requireContext(),
                if (result) R.string.sign_up_result_success else R.string.sign_up_result_error,
                Toast.LENGTH_LONG
        ).show()
    }

    private class TextDataWatcher(private val ctx: SignUpRecipeFragment) : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            ctx.validateFormTextWatcher()
        }
    }
}