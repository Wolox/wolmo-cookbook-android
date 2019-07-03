package ar.com.wolox.android.cookbook.koin

import android.content.Intent
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.datasync.DataSyncRecipeActivity
import ar.com.wolox.android.cookbook.koin.core.BaseFragment
import kotlinx.android.synthetic.main.fragment_koin_login.vKoinLoginEmailInput
import kotlinx.android.synthetic.main.fragment_koin_login.vKoinLoginLoginBtn
import kotlinx.android.synthetic.main.fragment_koin_login.vKoinLoginPasswordInput
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class KoinLoginRecipeFragment : BaseFragment<KoinLoginRecipePresenter>(), KoinLoginRecipeView {

    /**
     * You can send parameters to be injected.
     * Check how it's done at [koinLoginModule]
     */
    override val presenter: KoinLoginRecipePresenter by inject { parametersOf(this) }

    override val layout = R.layout.fragment_koin_login

    override fun init() = vKoinLoginLoginBtn.setOnClickListener {
        presenter.onLoginButtonClick(vKoinLoginEmailInput.text.toString(), vKoinLoginPasswordInput.text.toString())
    }

    // This will go to DataSyncRecipe just because
    override fun goToNextWindow() =
        requireActivity().startActivity(Intent(requireContext(), DataSyncRecipeActivity::class.java))

    override fun showLoginError() = toastFactory.show(R.string.koin_login_login_error)

    override fun showEmptyEmailError() {
        vKoinLoginEmailInput.error = getString(R.string.koin_login_empty_field)
    }

    override fun showInvalidEmailError() {
        vKoinLoginEmailInput.error = getString(R.string.koin_login_invalid_email)
    }

    override fun showEmptyPasswordError() {
        vKoinLoginPasswordInput.error = getString(R.string.koin_login_empty_field)
    }
}