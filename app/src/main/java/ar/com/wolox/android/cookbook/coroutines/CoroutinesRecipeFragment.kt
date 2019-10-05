package ar.com.wolox.android.cookbook.coroutines

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.util.ToastFactory
import kotlinx.android.synthetic.main.fragment_coroutines_login.*
import javax.inject.Inject

class CoroutinesRecipeFragment : WolmoFragment<CoroutinesRecipePresenter>(), CoroutinesRecipeView {

    @Inject
    internal lateinit var toastFactory: ToastFactory

    override fun layout() = R.layout.fragment_coroutines_login

    override fun init() {
        vCoroutinesLoginBtn.setOnClickListener {
            presenter.onLoginButtonClick(vCoroutinesEmailInput.text.toString(), vCoroutinesPasswordInput.text
                    .toString())
        }
    }

    override fun showWelcomeMessage(name: String) = toastFactory.show(getString(R.string.coroutines_welcome, name))

    override fun showLoginError() = toastFactory.show(R.string.coroutines_login_error)

    override fun showEmptyEmailError() {
        vCoroutinesEmailInput.error = getString(R.string.coroutines_empty_field)
    }

    override fun showInvalidEmailError() {
        vCoroutinesEmailInput.error = getString(R.string.coroutines_invalid_email)
    }

    override fun showEmptyPasswordError() {
        vCoroutinesPasswordInput.error = getString(R.string.coroutines_empty_field)
    }

    companion object {

        fun newInstance() = CoroutinesRecipeFragment()
    }
}