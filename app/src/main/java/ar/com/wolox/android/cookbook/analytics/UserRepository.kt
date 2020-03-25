package ar.com.wolox.android.cookbook.analytics

data class User(val name: String, val email: String, val password: String, val age: Int)

interface UserRepository {

    @Throws(ServiceUnavailableException::class)
    suspend fun getUser(email: String, password: String): User?
}

object ServiceUnavailableException : Exception()
