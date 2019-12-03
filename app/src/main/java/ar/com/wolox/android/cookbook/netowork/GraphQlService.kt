package ar.com.wolox.android.cookbook.netowork

import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.wolox.queries.GetOrdersQuery
import okhttp3.OkHttpClient

object GraphQlService {

    const val SERVER_URL = "https://graphql-training-wolox.herokuapp.com/graphql/"

    private val apolloClient = ApolloClient.builder()
            .serverUrl(SERVER_URL)
            .okHttpClient(OkHttpClient.Builder().build())
            .build()

    fun getOrders(page: Int, limit: Int): ApolloCall<GetOrdersQuery.Data> {
        return apolloClient.query(
                GetOrdersQuery
                        .builder()
                        .page(page)
                        .limit(limit)
                        .build())
    }
}
