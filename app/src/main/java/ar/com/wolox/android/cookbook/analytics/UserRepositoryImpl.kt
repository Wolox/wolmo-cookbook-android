
package ar.com.wolox.android.cookbook.analytics

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

// This is a mocked repository used just to test analytics
class UserRepositoryImpl @Inject constructor() : UserRepository {

    // Randomly say if the service is working or not.
    private val isServiceAvailable
        get() = (0..100).random() < 80

    private val mockedUserList = listOf(
        User("pepito", "pepito@gmail.com", "1234", 64),
        User("juanita", "juanita@gmail.com", "0000", 83),
        User("matias", "matias@gmail.com", "password", 17),
        User("laura", "laura@gmail.com", "abcdef", 36))

    // It's using the IO dispatcher to simulate the real task.
    override suspend fun getUser(email: String, password: String) = withContext(Dispatchers.IO) {
        if (!isServiceAvailable) {
            throw ServiceUnavailableException
        }

        mockedUserList.firstOrNull {
            it.email == email && it.password == password
        }
    }
}
