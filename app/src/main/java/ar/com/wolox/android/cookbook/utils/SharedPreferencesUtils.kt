package ar.com.wolox.android.cookbook.utils

import ar.com.wolox.wolmo.core.di.scopes.ApplicationScope
import ar.com.wolox.wolmo.core.util.SharedPreferencesManager
import javax.inject.Inject

@ApplicationScope
class SharedPreferencesUtils @Inject
constructor(private val sharedPreferencesManager: SharedPreferencesManager) {

    var hasReadInfo: Boolean
        get() = sharedPreferencesManager.get(INFO_READ_KEY, false)
        set(hasReadInfo) {
            sharedPreferencesManager.store(INFO_READ_KEY, hasReadInfo)
        }

    companion object {
        private const val INFO_READ_KEY = "read_info_key"
    }
}
