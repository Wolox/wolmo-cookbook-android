package ar.com.wolox.android.cookbook

import ar.com.wolox.android.cookbook.common.di.CookbookNetworkingComponent
import ar.com.wolox.android.cookbook.common.di.DaggerAppComponent
import ar.com.wolox.android.cookbook.common.di.DaggerCookbookNetworkingComponent
import ar.com.wolox.wolmo.core.WolmoApplication
import com.facebook.drawee.backends.pipeline.Fresco
import com.google.gson.FieldNamingPolicy
import com.readystatesoftware.chuck.ChuckInterceptor
import com.squareup.leakcanary.LeakCanary
import dagger.android.AndroidInjector
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level

class CookbookApplication : WolmoApplication() {

    override fun onInit() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        // Initialize Application stuff here
        initializeLeakCanary()
        initializeFresco()
    }

    private fun initializeLeakCanary() {
        LeakCanary.install(this)
    }

    private fun initializeFresco() {
        Fresco.initialize(this)
    }

    override fun applicationInjector(): AndroidInjector<CookbookApplication> {
        return DaggerAppComponent.builder()
                .networkingComponent(buildNetworkingComponent())
                .sharedPreferencesName(SHARED_PREFERENCES_NAME)
                .application(this)
                .create(this)
    }

    private fun buildNetworkingComponent(): CookbookNetworkingComponent {
        val builder = DaggerCookbookNetworkingComponent.builder()
                .baseUrl(PLACEHOLDER_URL)
                .gsonNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)

        if (BuildConfig.DEBUG) {
            builder.okHttpInterceptors(arrayOf(
                    buildHttpLoggingInterceptor(Level.BODY),
                    ChuckInterceptor(this)))
        }

        return builder.build()
    }

    /**
     * Returns a [HttpLoggingInterceptor] with the newLevel given by **newLevel**.
     *
     * @param newLevel - Logging newLevel for the interceptor.
     * @return New instance of interceptor
     */
    private fun buildHttpLoggingInterceptor(newLevel: HttpLoggingInterceptor.Level): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply { this.level = newLevel }
    }

    private companion object {

        const val SHARED_PREFERENCES_NAME = "wolmo-cookbook-sp"
        const val PLACEHOLDER_URL = "https://jsonplaceholder.typicode.com"
    }
}
