package ar.com.wolox.android.cookbook.coroutines

import android.util.Log
import ar.com.wolox.android.cookbook.common.network.networkCallback
import ar.com.wolox.android.cookbook.coroutines.exceptions.CallFailureException
import ar.com.wolox.android.cookbook.coroutines.exceptions.ResponseFailedException

class CoroutinesUsersRepository(private val usersService: UsersService) {

    fun getUser(email: String, password: String, onSuccess: (CoroutinesUser?) -> Unit) {
        usersService.getUser(email, password).enqueue(networkCallback {

            onResponseSuccessful {
                Log.d("DylanLog", "Success!")
                onSuccess(it?.let { users -> if (users.isNotEmpty()) users[0] else null }) }

            onResponseFailed { _, _ -> throw ResponseFailedException() }

            onCallFailure { throw it ?: CallFailureException() }
        })
    }
}