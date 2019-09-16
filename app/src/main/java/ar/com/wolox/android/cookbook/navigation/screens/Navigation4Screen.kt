package ar.com.wolox.android.cookbook.navigation.screens

import me.aartikov.alligator.Screen
import java.io.Serializable

class Navigation4Screen constructor(private val message: String) : Screen, Serializable {

    fun getMessage(): String = message
}
