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
        vWebView.settings.javaScriptEnabled = true
        vWebView.settings.useWideViewPort = true
        vWebView.settings.loadWithOverviewMode = true
        vWebView.loadUrl(url)
        vWebView.webViewClient = object : WebViewClient() {

            var authComplete = false
            var token: String? = null

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                if (url != null && url.contains("#access_token=")) {
                    val uri = Uri.parse(url)
                    token = uri.encodedFragment
                    if (token != null) {
                        val accessToken = token!!.substring(token!!.lastIndexOf("=") + 1)
                        authComplete = true
                        listener.onCodeReceived(accessToken)
                        dismiss()
                    } else {
                        listener.onCodeError()
                        dismiss()
                    }
                } else if (url != null && url.contains("?error")) {
                    listener.onApiError()
                    dismiss()
                }
            }
        }
        this.show()
    }
}
