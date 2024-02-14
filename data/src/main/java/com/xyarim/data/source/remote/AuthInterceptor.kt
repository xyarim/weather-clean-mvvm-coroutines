package com.xyarim.data.source.remote

import com.xyarim.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

internal class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url =
            chain.request().url.newBuilder()
                .addQueryParameter(AUTH_KEY_NAME, BuildConfig.API_KEY)
                .addQueryParameter(QUERY_PARAM_KEY, QUERY_PARAM_VALUE)
                .build()
        val request = chain.request().newBuilder().url(url).build()
        return chain.proceed(request)
    }

    private companion object {
        private const val AUTH_KEY_NAME = "key"
        private const val QUERY_PARAM_KEY = "q"
        private const val QUERY_PARAM_VALUE = "Tashkent"
    }
}