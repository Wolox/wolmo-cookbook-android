package ar.com.wolox.android.cookbook.coroutines.networking

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CoroutinesUsersRepository(private val usersService: UsersService) {

    /**
     * It suspends the current coroutine on the Dispatchers.IO context, calls the API
     * and returns a [CoroutinesUser] with the given email and password if it exists.
     */
    suspend fun getUser(email: String, password: String) = withContext(Dispatchers.IO) {
        usersService.withToken { getUser(it, email, password).firstOrNull() }
    }
}