package com.finderbar.hilsa.core.network.interceptor

import com.finderbar.hilsa.core.network.contract.TokenProvider
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

/**
 * An OkHttp Interceptor that automatically attaches a Bearer token to all outgoing requests.
 * Uses [TokenProvider] to retrieve the token synchronously via [runBlocking].
 */
class AuthInterceptor(
    private val tokenProvider: TokenProvider
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        // Synchronously fetch the token for the interceptor
        val token = runBlocking {
            tokenProvider.getToken()
        }

        val modifiedRequest = originalRequest.newBuilder().apply {
            if (!token.isNullOrEmpty()) {
                addHeader("Authorization", "Bearer $token")
            }
        }.build()

        return chain.proceed(modifiedRequest)
    }
}
