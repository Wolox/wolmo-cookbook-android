package ar.com.wolox.android.cookbook.mercadopago.network

import ar.com.wolox.android.cookbook.mercadopago.model.Product
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test

class MercadoPagoProductsRepositoryTest {

    private lateinit var repository: MercadoPagoProductsRepository
    private lateinit var service: MercadoPagoProductsService

    @Before
    fun setup() {
        service = mock()
        repository = MercadoPagoProductsRepository(service)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `given a list of products when get products then it returns the list`() = runBlockingTest {

        // GIVEN
        val list = listOf<Product>(mock(), mock(), mock())
        whenever(repository.getProducts()).thenReturn(list)

        // WHEN
        val result = repository.getProducts()

        // THEN
        MatcherAssert.assertThat(result, `is`(list))
    }
}