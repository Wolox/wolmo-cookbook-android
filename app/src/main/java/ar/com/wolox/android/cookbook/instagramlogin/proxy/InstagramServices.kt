package ar.com.wolox.android.cookbook.instagramlogin.proxy

import ar.com.wolox.android.cookbook.instagramlogin.model.InstagramResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface InstagramServices {

    @GET("/v1/users/self/media/recent")
    fun getAPIData(@Query("access_token") token: String): Call<InstagramResponse>
}