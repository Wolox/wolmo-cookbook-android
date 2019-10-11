package ar.com.wolox.android.cookbook.coroutines.core

import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES
import android.text.Html
import android.text.Spanned

fun String.fromHtml(): Spanned {
    return if (SDK_INT >= VERSION_CODES.N) {
        Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
    } else {
        @Suppress("DEPRECATION")
        Html.fromHtml(this)
    }
}
