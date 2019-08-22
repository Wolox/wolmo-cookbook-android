package ar.com.wolox.android.cookbook.instagramlogin.proxy

import android.util.Log
import ar.com.wolox.android.cookbook.instagramlogin.model.InstagramResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class InstagramProxy @Inject constructor() {

    fun getInstagramData(accessToken: String, listener: InstagramProxyListener) {
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val api = retrofit.create(InstagramServices::class.java)
        val call = api.getAPIData(accessToken)
        call.enqueue(object : Callback<InstagramResponse> {
            override fun onResponse(call: Call<InstagramResponse>, response: Response<InstagramResponse>) {
                Log.e("IG", "ProxyResponse: Success")
                if (response.body() != null) {
                    val result = response.body()!!.data
                    listener.onResponse(result)
                } else {
                    listener.onError()
                }
            }

            override fun onFailure(call: Call<InstagramResponse>, t: Throwable) {
                listener.onFail(t.message)
            }
        })
    }

    companion object {
        private const val BASE_URL = "https://api.instagram.com"
    }
}
