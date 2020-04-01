package ar.com.wolox.android.cookbook.scanqr.scansuccess

import android.os.Bundle
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.scanqr.ScanQrActivity
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_scan_qr_success.*
import javax.inject.Inject

class ScanSuccessFragment @Inject constructor() : WolmoFragment<ScanSuccessPresenter>(), ScanSuccessView {

    @Inject
    lateinit var presenter: ScanSuccessPresenter

    override fun layout(): Int = R.layout.fragment_scan_qr_success

    override fun init() {
        presenter.attachView(this)
        presenter.init(arguments?.getString(RESULT_KEY))
        setListeners()
    }

    override fun setListeners() {
        super.setListeners()
        vGoToScanMenuButton.setOnClickListener {
            presenter.onScanMenuClicked()
        }
    }

    override fun setUi(result: String?) {
        vScanResult.text = result.orEmpty()
    }

    override fun goToScanMenu() = (activity as ScanQrActivity).showScanMenuFragment()

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