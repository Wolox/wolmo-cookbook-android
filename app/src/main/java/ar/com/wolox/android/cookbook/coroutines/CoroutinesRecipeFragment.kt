package ar.com.wolox.android.cookbook.coroutines

import android.content.Intent
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.datasync.DataSyncRecipeActivity
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

    // This will go to DataSyncRecipe just because
    override fun goToNextWindow() =
            requireActivity().startActivity(Intent(requireContext(), DataSyncRecipeActivity::class.java))

    override fun showLoginError() = toastFactory.show(R.string.test_login_login_error)

    override fun showEmptyEmailError() {
        vCoroutinesEmailInput.error = getString(R.string.test_login_empty_field)
    }

    override fun showInvalidEmailError() {
        vCoroutinesEmailInput.error = getString(R.string.test_login_invalid_email)
    }

    override fun showEmptyPasswordError() {
        vCoroutinesPasswordInput.error = getString(R.string.test_login_empty_field)
    }
}