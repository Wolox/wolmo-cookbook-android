package ar.com.wolox.android.cookbook.instagramlogin

import android.view.View
import android.webkit.CookieManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.databinding.FragmentInstagramLoginBinding
import ar.com.wolox.android.cookbook.instagramlogin.adapter.InstagramLoginAuthListener
import ar.com.wolox.android.cookbook.instagramlogin.adapter.InstagramLoginView
import ar.com.wolox.android.cookbook.instagramlogin.model.InstagramDataItem
import ar.com.wolox.android.cookbook.instagramlogin.model.TypeErrorMessage
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_instagram_login.*

class InstagramLoginRecipeFragment :
    WolmoFragment<FragmentInstagramLoginBinding, InstagramLoginRecipePresenter>(),
    InstagramLoginRecipeView {

    private lateinit var viewAdapter: InstagramLoginRecipeAdapter
    private var viewManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
    private lateinit var pictureList: MutableList<InstagramDataItem>

    override fun layout(): Int = R.layout.fragment_instagram_login

    override fun init() {
    }

    override fun setListeners() {
        super.setListeners()
        with(binding!!) {
            vSessionBtn.setOnClickListener {
                presenter.onSessionButtonClicked()
            }

            vDataBtn.setOnClickListener {
                presenter.onFetchDataButtonClicked()
            }
        }
    }

    override fun enableLoginBtn() {
        binding!!.vSessionBtn.text = getString(R.string.instagram_login_btn)
    }

    override fun enableLogoutBtn() {
        binding!!.vSessionBtn.text = getString(R.string.instagram_logout_btn)
    }

    override fun igLogout() {
        CookieManager.getInstance().removeAllCookies { p0 ->
            presenter.onLogoutResponse(p0)
        }
    }

    override fun showLoginSuccessMsg() {
        Toast.makeText(context, getString(R.string.instagram_login_success), Toast.LENGTH_SHORT)
            .show()
    }

    override fun showLogoutSuccessMsg() {
        Toast.makeText(context, getString(R.string.instagram_logout_success), Toast.LENGTH_SHORT)
            .show()
    }

    override fun deleteListData() {
        pictureList = mutableListOf()
        binding!!.vRecyclerView.visibility = View.INVISIBLE
    }

    override fun showWebView(url: String) {
        InstagramLoginView().showDialog(requireContext(), url, object : InstagramLoginAuthListener {
            override fun onCodeReceived(authToken: String) {
                presenter.onLoginSuccessResponse(authToken)
            }

            override fun onCodeError() {
                presenter.onLoginErrorResponse()
            }

            override fun onApiError() {
                presenter.onLoginFailResponse()
            }
        })
    }

    override fun showIGData(data: List<InstagramDataItem>) {
        binding!!.vRecyclerView.visibility = View.VISIBLE
        pictureList = mutableListOf()
        pictureList.addAll(data)

        viewAdapter = InstagramLoginRecipeAdapter(pictureList)
        binding!!.vRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        viewAdapter.notifyDataSetChanged()
    }

    override fun showFailInService(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

    override fun showError(type: TypeErrorMessage) {
        val message: String = when (type) {
            TypeErrorMessage.LOGIN -> {
                getString(R.string.instagram_error_login)
            }
            TypeErrorMessage.LOGOUT -> {
                getString(R.string.instagram_error_cookies)
            }
            TypeErrorMessage.NETWORK_UNAVAILABLE -> {
                getString(R.string.instagram_error_network_unavailable)
            }
            TypeErrorMessage.SERVICE -> {
                getString(R.string.instagram_error_api)
            }
            TypeErrorMessage.DATA -> {
                getString(R.string.instagram_error_fetch_data)
            }
        }

        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}