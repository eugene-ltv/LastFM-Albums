package com.saiferwp.lastfmalbums.data.api.interceptor

import com.saiferwp.lastfmalbums.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val newUrl = request.url()
            .newBuilder()
            .addQueryParameter(API_KEY, BuildConfig.LastFmApiKey)
            .build()

        val newRequest =
            request.newBuilder()
                .url(newUrl)
                .build()

        return chain.proceed(newRequest)
    }

    companion object {
        const val API_KEY = "api_key"
    }
}