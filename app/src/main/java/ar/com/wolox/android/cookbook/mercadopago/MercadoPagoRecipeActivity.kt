package ar.com.wolox.android.cookbook.mercadopago

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.activity.WolmoActivity

class MercadoPagoRecipeActivity : WolmoActivity() {

    override fun layout(): Int = R.layout.activity_base

    override fun init() = replaceFragment(R.id.vActivityBaseContent, MercadoPagoRecipeFragment.newInstance())
}