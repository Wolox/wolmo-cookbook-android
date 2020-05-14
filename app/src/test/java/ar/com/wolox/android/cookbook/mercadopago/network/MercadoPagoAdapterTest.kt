package ar.com.wolox.android.cookbook.mercadopago.network

import ar.com.wolox.android.cookbook.mercadopago.model.CheckoutPreference
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.nullValue
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException

class MercadoPagoAdapterTest {

    private lateinit var adapter: MercadoPagoAdapter
    private lateinit var service: MercadoPagoService

    @Before
    fun setup() {
        service = mock()
        adapter = MercadoPagoAdapter(service)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `given a preference when checkout then it returns the preference`() = runBlockingTest {

        // GIVEN
        val preference = CheckoutPreference("ASDfgfhdjksda23i12o839")
        whenever(adapter.checkout(any())).thenReturn(preference)

        // WHEN
        val result = adapter.checkout(mock())

        // THEN
        assertThat(result, `is`(preference))
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `given an error when checkout then it returns null`() = runBlockingTest {

        // GIVEN
        whenever(adapter.checkout(any())).thenThrow(mock<HttpException>())

        // WHEN
        val result = adapter.checkout(mock())

        // THEN
        assertThat(result, `is`(nullValue()))
    }
}