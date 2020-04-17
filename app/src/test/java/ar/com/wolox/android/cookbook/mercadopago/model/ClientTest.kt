package ar.com.wolox.android.cookbook.mercadopago.model

import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ClientTest {

    @Test
    fun `given a valid email when initialized then the client is well initialized`() {

        // GIVEN
        val email = "example@gmail.com"

        // WHEN
        Client("Pepito", email)

        // THEN
        // no exception is thrown
    }

    @Test(expected = IllegalArgumentException::class)
    fun `given an invalid email when initialized then an exception is thrown`() {

        // GIVEN
        val email = "examplegmail.com"

        // WHEN
        Client("Pepito", email)

        // THEN
        // exception is thrown
    }
}