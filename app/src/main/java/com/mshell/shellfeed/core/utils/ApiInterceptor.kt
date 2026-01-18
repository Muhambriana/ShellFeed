package com.mshell.shellfeed.core.utils

import com.mshell.shellfeed.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val requestWithHeaders = originalRequest.newBuilder()
            .addHeader("User-Agent", "ShellFeed/${BuildConfig.VERSION_CODE}")
            .addHeader("Accept", "application/json")
            .addHeader("X-Api-Key", NetworkInfo.API_KEY) // NewsAPI supports this header
            .build()

        return chain.proceed(requestWithHeaders)
    }
}