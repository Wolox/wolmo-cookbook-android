package ar.com.wolox.android.cookbook.scanqr.scansuccess

import android.os.Bundle
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.scanqr.ScanQrActivity
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_scan_qr_success.*
import javax.inject.Inject

class ScanSuccessFragment @Inject constructor() : WolmoFragment<ScanSuccessPresenter>(), ScanSuccessView {

    override fun init() {
        presenter.init(arguments?.getString(RESULT_KEY))
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        bScanMenu.setOnClickListener {
            presenter.onScanMenuClicked()
        }
    }

    override fun setUi(result: String?) {
        vScanResult.text = result.orEmpty()
    }

    override fun goToScanMenu() {
        (activity as ScanQrActivity).goToScanMenuFragment()
    }

    override fun layout(): Int = R.layout.fragment_scan_qr_success

    companion object {
        fun newInstance(result: String?): ScanSuccessFragment {
            val bundle: Bundle = Bundle().apply {
                putString(RESULT_KEY, result)
            }

            return ScanSuccessFragment().apply {
                arguments = bundle
            }
        }

        private const val RESULT_KEY = "result"
    }
}