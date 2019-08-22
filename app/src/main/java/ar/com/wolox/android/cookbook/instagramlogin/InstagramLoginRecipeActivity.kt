package ar.com.wolox.android.cookbook.instagramlogin

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.activity.WolmoActivity

/**
 * To connect app with InstagramApi:
 * 1. Register app in "https://www.instagram.com/developer/"
 * 2. In "Security" tab:
 *  2.a) Uncheck "Disable implicit OAuth" option
 *  2.b) Complete with valid redirect URI
 * 3. Save API Key (Client ID)
 * 4. Replace Client ID and redirect uri in "InstagramLoginRecipePresenter" constants
 *
 * Note: All personal data from Api its not parsed in the example.
 *      Go to "app > assets > InstagramResponseExample.json" for a full response
 *      json contains: own images, tags, positions, descriptions, likes, comments, etc...
 */
class InstagramLoginRecipeActivity : WolmoActivity() {

    override fun layout(): Int = R.layout.activity_base

    override fun init() = replaceFragment(R.id.vActivityBaseContent, InstagramLoginRecipeFragment())
}