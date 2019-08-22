package ar.com.wolox.android.cookbook.instagramlogin

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.view.View
import android.webkit.CookieManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.instagramlogin.adapter.InstagramLoginAuthListener
import ar.com.wolox.android.cookbook.instagramlogin.adapter.InstagramLoginView
import ar.com.wolox.android.cookbook.instagramlogin.model.InstagramDataItem
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_instagram_login.*

class InstagramLoginRecipeFragment : WolmoFragment<InstagramLoginRecipePresenter>(), InstagramLoginRecipeView {

    private lateinit var viewAdapter: InstagramLoginRecipeAdapter
    private var viewManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
    private lateinit var pictureList: MutableList<InstagramDataItem>

    override fun layout(): Int = R.layout.fragment_instagram_login

    override fun init() {
    }

    override fun setListeners() {
        super.setListeners()

        vLoginBtn.setOnClickListener {
            presenter.onIgLoginRequest()
        }

        vDataBtn.setOnClickListener {
            presenter.onFetchDataRequest()
        }
    }

    override fun enableLoginBtn() {
        vLoginBtn.text = getString(R.string.instagram_login_btn)
    }

    override fun enableLogoutBtn() {
        vLoginBtn.text = getString(R.string.instagram_logout_btn)
    }

    override fun igLogout() {
        CookieManager.getInstance().removeAllCookies { p0 ->
            presenter.onLogoutResponse(p0)
        }
    }

    override fun showLoginError() {
        Toast.makeText(context, getString(R.string.instagram_error_login), Toast.LENGTH_LONG).show()
    }

    override fun showLogoutError() {
        Toast.makeText(context, getString(R.string.instagram_error_cookies), Toast.LENGTH_LONG).show()
    }

    override fun showLoginSuccessMsg() {
        Toast.makeText(context, getString(R.string.instagram_login_success), Toast.LENGTH_SHORT).show()
    }

    override fun showLogoutSuccessMsg() {
        Toast.makeText(context, getString(R.string.instagram_logout_success), Toast.LENGTH_SHORT).show()
    }

    override fun isNetworkAvailable(): Boolean {
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager) {
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected ?: false
        } else false
    }

    override fun showNetworkUnavailableError() {
        Toast.makeText(context, getString(R.string.instagram_error_network_unavailable), Toast.LENGTH_LONG).show()
    }

    override fun showWebView(url: String) {
        if (context != null) {
            InstagramLoginView().showDialog(context!!, url, object : InstagramLoginAuthListener {
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
    }

    override fun showIGData(data: List<InstagramDataItem>) {
        vRecyclerView.visibility = View.VISIBLE
        pictureList = mutableListOf()
        pictureList.addAll(data)

        viewAdapter = InstagramLoginRecipeAdapter(pictureList)
        vRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        viewAdapter.notifyDataSetChanged()
    }

    override fun showErrorInService() {
        Toast.makeText(context, getString(R.string.instagram_error_api), Toast.LENGTH_LONG).show()
    }

    override fun showFailInService(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

    override fun showFetchDataError() {
        Toast.makeText(context, getString(R.string.instagram_error_fetch_data), Toast.LENGTH_LONG).show()
    }
}