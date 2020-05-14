package ar.com.wolox.android.cookbook.mercadopago.model

import com.nhaarman.mockitokotlin2.mock
import org.junit.Test

class CartTest {

    @Test
    fun `given a non-empty list of products quantities when initialized then the cart is well initialized`() {

        // GIVEN
        val list = listOf<ProductQuantity>(mock())

        // WHEN
        Cart(mock(), list)

        // THEN
        // no exception is thrown
    }

    @Test(expected = IllegalArgumentException::class)
    fun `given an empty list of products quantities when initialized then an exception is thrown`() {

        // GIVEN
        val list = emptyList<ProductQuantity>()

        // WHEN
        Cart(mock(), list)

        // THEN
        // exception is thrown
    }
}