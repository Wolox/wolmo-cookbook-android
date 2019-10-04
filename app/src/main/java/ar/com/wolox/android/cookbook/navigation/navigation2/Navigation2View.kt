package ar.com.wolox.android.cookbook.navigation.navigation2

import android.content.Context
import ar.com.wolox.android.cookbook.R
import com.wealthfront.magellan.BaseScreenView

class Navigation2View(context: Context?) : BaseScreenView<Navigation2Screen>(context) {

    init {
        inflate(context, R.layout.fragment_navigation2, this)
    }
}
