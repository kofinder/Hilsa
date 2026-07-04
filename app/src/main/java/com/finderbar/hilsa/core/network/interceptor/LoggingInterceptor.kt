package com.finderbar.hilsa.core.network.interceptor

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import okio.Buffer

/**
 * Author: Ko Thein (Nathan Mratt)
 * Created: 7/4/2026
 * Project: Hilsa
 */
class LoggingInterceptor(
    private val isDebug: Boolean = true
) : Interceptor {

    companion object {
        private const val TAG = "NetworkLogger"
    }

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()

        if (isDebug) {
            logRequest(request)
        }

        val response = chain.proceed(request)

        if (isDebug) {
            logResponse(response)
        }

        return response
    }

    /**
     * Logs HTTP request details including method, URL, headers, and body (if small).
     */
    private fun logRequest(request: okhttp3.Request) {

        Log.d(TAG, "────── REQUEST ──────")
        Log.d(TAG, "METHOD : ${request.method}")
        Log.d(TAG, "URL    : ${request.url}")

        request.headers.forEach {
            Log.d(TAG, "HEADER : ${it.first} = ${it.second}")
        }

        request.body?.let { body ->

            try {
                val buffer = Buffer()
                body.writeTo(buffer)

                Log.d(TAG, "BODY   : ${buffer.readUtf8()}")
            } catch (e: Exception) {
                Log.d(TAG, "BODY   : (unable to read)")
            }
        }
    }

    /**
     * Logs HTTP response details including status code and URL.
     */
    private fun logResponse(response: Response) {

        val request = response.request

        Log.d(TAG, "────── RESPONSE ──────")
        Log.d(TAG, "URL    : ${request.url}")
        Log.d(TAG, "CODE   : ${response.code}")
        Log.d(TAG, "MESSAGE: ${response.message}")
    }
}