package com.finderbar.hilsa.core.network.interceptor

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Author: Ko Thein (Nathan Mratt)
 * Created: 7/4/2026
 * Project: Hilsa
 */
class TimingInterceptor : Interceptor {

    companion object {
        private const val TAG = "NetworkTiming"
    }

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()

        val startTime = System.nanoTime()

        val response = chain.proceed(request)

        val endTime = System.nanoTime()

        val durationMs = (endTime - startTime) / 1_000_000

        Log.d(
            TAG,
            "TIME → ${request.method} ${request.url} took ${durationMs}ms"
        )

        return response
    }
}