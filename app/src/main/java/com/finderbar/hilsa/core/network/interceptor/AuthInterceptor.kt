package com.finderbar.hilsa.core.network.interceptor

import com.finderbar.hilsa.core.network.contract.TokenProvider
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Author: Ko Thein (Nathan Mratt)
 * Created: 7/4/2026
 * Project: Hilsa
 */
class AuthInterceptor(
    private val tokenProvider: TokenProvider
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest = chain.request()

        val token = runBlocking {
            tokenProvider.getToken()
        }

        val modifiedRequest = originalRequest.newBuilder().apply {

            // Attach token only if available
            if (!token.isNullOrEmpty()) {
                addHeader("Authorization", "Bearer $token")
            }

        }.build()

        return chain.proceed(modifiedRequest)
    }
}