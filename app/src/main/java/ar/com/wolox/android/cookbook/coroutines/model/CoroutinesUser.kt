package ar.com.wolox.android.cookbook.coroutines.model

import java.io.Serializable

data class CoroutinesUser(
    val id: Long,
    val username: String,
    val email: String,
    val password: String,
    val picture: String,
    val cover: String,
    val description: String,
    val location: String,
    val name: String,
    val phone: String
) : Serializable