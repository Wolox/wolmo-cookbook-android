package ar.com.wolox.android.cookbook.tests

import android.content.Intent
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.databinding.FragmentTestLoginBinding
import ar.com.wolox.android.cookbook.datasync.DataSyncRecipeActivity
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.util.ToastFactory
import kotlinx.android.synthetic.main.fragment_test_login.vTestLoginEmailInput
import kotlinx.android.synthetic.main.fragment_test_login.vTestLoginLoginBtn
import kotlinx.android.synthetic.main.fragment_test_login.vTestLoginPasswordInput
import javax.inject.Inject

class TestLoginRecipeFragment : WolmoFragment<FragmentTestLoginBinding, TestLoginRecipePresenter>(), TestLoginRecipeView {

    @Inject
    internal lateinit var toastFactory: ToastFactory

    override fun layout() = R.layout.fragment_test_login

    override fun init() {
        with(binding!!) {
            vTestLoginLoginBtn.setOnClickListener {
                presenter.onLoginButtonClick(
                    vTestLoginEmailInput.text.toString(),
                    vTestLoginPasswordInput.text.toString()
                )
            }
        }
    }

    // This will go to DataSyncRecipe just because
    override fun goToNextWindow() =
            requireActivity().startActivity(Intent(requireContext(), DataSyncRecipeActivity::class.java))

    override fun showLoginError() = toastFactory.show(R.string.test_login_login_error)

    override fun showEmptyEmailError() {
        binding!!.vTestLoginEmailInput.error = getString(R.string.test_login_empty_field)
    }

    override fun showInvalidEmailError() {
        binding!!.vTestLoginEmailInput.error = getString(R.string.test_login_invalid_email)
    }

    override fun showEmptyPasswordError() {
        binding!!.vTestLoginPasswordInput.error = getString(R.string.test_login_empty_field)
    }
}