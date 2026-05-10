package com.example.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NetworkInterceptor @Inject constructor(
    private val networkHandler: NetworkHandler,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        networkHandler.showToast()
        return chain.proceed(request)
    }
}