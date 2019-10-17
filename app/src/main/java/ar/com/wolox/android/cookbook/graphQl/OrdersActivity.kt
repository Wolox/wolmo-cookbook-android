package ar.com.wolox.android.cookbook.graphQl

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.activity.WolmoActivity
import kotlinx.android.synthetic.main.activity_graph_ql.*

class OrdersActivity : WolmoActivity() {

    override fun init() = replaceFragment(R.id.vActivityFrameLayout, OrdersFragment())

    override fun layout(): Int = R.layout.activity_graph_ql
}
