package ar.com.wolox.android.cookbook.instagramlogin.adapter

import android.app.Dialog
import android.content.Context
import android.net.Uri
import android.webkit.WebView
import android.webkit.WebViewClient
import ar.com.wolox.android.cookbook.R
import kotlinx.android.synthetic.main.item_web_view.*
import javax.inject.Inject

class InstagramLoginView @Inject constructor() {

    fun showDialog(context: Context, url: String, listener: InstagramLoginAuthListener) = Dialog(context).apply {
        setCancelable(true)
        setCanceledOnTouchOutside(false)
        setContentView(R.layout.item_web_view)

        vWebView.settings.run {
            javaScriptEnabled = true
            useWideViewPort = true
            loadWithOverviewMode = true
        }
        vWebView.loadUrl(url)
        vWebView.webViewClient = object : WebViewClient() {

            var authComplete = false
            var token: String? = null

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                url?.let { it ->
                    if (it.contains(SUCCESS_KEY)) {

                        val uri = Uri.parse(it)
                        token = uri.encodedFragment

                        token?.let { lambda ->

                            val accessToken = lambda.substring(lambda.lastIndexOf("=") + 1)
                            authComplete = true
                            listener.onCodeReceived(accessToken)
                            dismiss()
                        } ?: run {
                            listener.onCodeError()
                            dismiss()
                        }
                    } else if (it.contains(ERROR_KEY)) {
                        listener.onApiError()
                        dismiss()
                    }
                }
            }
        }
        this.show()
    }

    companion object {
        private const val SUCCESS_KEY = "#access_token="
        private const val ERROR_KEY = "?error"
    }
}
