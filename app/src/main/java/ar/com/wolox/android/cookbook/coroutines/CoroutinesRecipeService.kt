package ar.com.wolox.android.cookbook.coroutines

import android.os.AsyncTask
import ar.com.wolox.wolmo.core.di.scopes.ApplicationScope
import javax.inject.Inject

@ApplicationScope
open class CoroutinesRecipeService @Inject constructor() {

    /**
     * This task should make the login against something like an API or a DB,
     * but this doesn't concern the tests so it will always returns a hardcoded user with the received email.
     */
    private class LoginTask constructor(
        private val email: String,
        private val password: String,
        private val onSuccess: (CoroutinesUserModel) -> Unit,
        private val onError: () -> Unit
    ) : AsyncTask<Void, Void, CoroutinesUserModel?>() {

        override fun doInBackground(vararg params: Void?) = CoroutinesUserModel(email)

        override fun onPostExecute(user: CoroutinesUserModel?) = user?.let { onSuccess(it) } ?: onError()
    }

    fun login(
        email: String,
        password: String,
        onSuccess: (CoroutinesUserModel) -> Unit,
        onError: () -> Unit
    ) {
        LoginTask(email, password, onSuccess, onError).execute()
    }
}