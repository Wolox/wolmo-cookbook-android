package ar.com.wolox.android.cookbook.twitterlogin

import android.content.Intent
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.activity.WolmoActivity

/**
 * To connect app with TwitterApi:
 * 1. Register app in "https://developer.twitter.com/en/apps"
 * 2. Required fields (to obtain all data from api, like user email):
 * "Callback URL" (complete with "twittersdk://", "Terms of service URL" and "Privacy policy URL"
 * 3. App permissions: Enable "Request email address"
 * 4. Save "API Key" and "API Secret Key" from keys and tokens view
 * 5. Initialize TwitterApp in application (see "Note" at bottom)
 */
class TwitterLoginRecipeActivity : WolmoActivity() {

    override fun layout(): Int = R.layout.activity_base

    override fun init() = replaceFragment(R.id.vActivityBaseContent, TwitterLoginRecipeFragment())

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        supportFragmentManager.findFragmentById(R.id.vActivityBaseContent)
                ?.onActivityResult(requestCode, resultCode, data)
    }
}

/**
 * Note: In WolmoApplication, initialize Twitter App:
 *
 * val config = TwitterConfig.Builder(this)
 *              .logger(DefaultLogger(Log.DEBUG)) // enable logging when app is in debug mode
 *              .twitterAuthConfig(TwitterAuthConfig(API_KEY, API_SECRET_KEY))
 *              .debug(true) // enable debug mode
 *              .build()
 *              Twitter.initialize(config)
 **/