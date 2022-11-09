package com.david.filmobil.network.connectivity

import com.david.filmobil.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {

    private companion object {
        private const val API_KEY = "api_key"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url
        val builder = chain.request().newBuilder()
        val modifiedUrl =
            builder.url(
                url.newBuilder()
                    .addQueryParameter(API_KEY, BuildConfig.API_KEY)
                    .build()
            ).build()
        return chain.proceed(modifiedUrl)
    }
}