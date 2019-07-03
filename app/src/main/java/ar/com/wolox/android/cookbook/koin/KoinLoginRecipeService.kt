package ar.com.wolox.android.cookbook.koin

import android.os.AsyncTask

open class KoinLoginRecipeService {

    /**
     * This task should make the login against something like an API or a DB,
     * but this doesn't concern the koin so it will always returns a hardcoded user with the received email.
     */
    private class LoginTask constructor(
        private val email: String,
        private val password: String,
        private val onSuccess: (KoinLoginUserModel) -> Unit,
        private val onError: () -> Unit
    ) : AsyncTask<Void, Void, KoinLoginUserModel?>() {

        override fun doInBackground(vararg params: Void?) = KoinLoginUserModel(email)

        override fun onPostExecute(user: KoinLoginUserModel?) = user?.let { onSuccess(it) } ?: onError()
    }

    fun login(
        email: String,
        password: String,
        onSuccess: (KoinLoginUserModel) -> Unit,
        onError: () -> Unit
    ) {
        LoginTask(email, password, onSuccess, onError).execute()
    }
}