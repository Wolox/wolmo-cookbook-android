package ar.com.wolox.android.cookbook.koin.core

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

class ToastFactory(private val context: Context) {

    fun show(@StringRes resId: Int) = Toast.makeText(context, resId, Toast.LENGTH_SHORT).show()

    fun show(message: String) = Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}