package ar.com.wolox.android.cookbook.koin.core

import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import org.koin.android.ext.android.inject

abstract class BaseActivity : AppCompatActivity() {

    protected val toastFactory: ToastFactory by inject()

    @LayoutRes
    abstract fun layout(): Int

    abstract fun init()

    protected fun replaceFragment(resId: Int, f: Fragment) {
        supportFragmentManager.beginTransaction().replace(resId, f).commit()
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout())
        init()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}