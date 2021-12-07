package ar.com.wolox.android.cookbook.fingerprint.success

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.databinding.FragmentFingerprintLoginSuccessBinding
import ar.com.wolox.android.cookbook.fingerprint.login.FingerprintLoginRecipeActivity
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.util.jumpTo
import kotlinx.android.synthetic.main.fragment_fingerprint_login_success.*

class FingerprintLoginSuccessFragment :
    WolmoFragment<FragmentFingerprintLoginSuccessBinding, FingerprintLoginSuccessPresenter>(),
    FingerprintLoginSuccessView {

    override fun init() {
    }

    override fun layout() = R.layout.fragment_fingerprint_login_success

    override fun setListeners() {
        binding!!.vBackButton.setOnClickListener {
            presenter.onBackButtonPressed()
        }
    }

    override fun goBack() {
        requireContext().jumpTo(FingerprintLoginRecipeActivity::class.java)
        requireActivity().finish()
    }

    companion object {
        fun newInstance() = FingerprintLoginSuccessFragment()
    }
}
