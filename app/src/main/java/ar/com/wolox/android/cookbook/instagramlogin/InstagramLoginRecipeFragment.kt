package ar.com.wolox.android.cookbook.instagramlogin

import android.view.View
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
}