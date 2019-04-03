package ar.com.wolox.android.cookbook.tests

import ar.com.wolox.wolmo.core.di.scopes.ApplicationScope
import javax.inject.Inject

@ApplicationScope
class TestLoginRecipeService @Inject constructor() {

    /**
     * This method should make the login and return the user or a null if user is invalid,
     * but this doesn't concern the tests so it will always returns a hardcoded user with the received email.
     */
    fun login(
        email: String,
        password: String
    ): TestLoginUserModel? = TestLoginUserModel(email)
}