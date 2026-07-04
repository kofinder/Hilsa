package com.finderbar.hilsa.core.network.retrofit

import com.finderbar.hilsa.core.network.interceptor.AuthInterceptor
import com.finderbar.hilsa.core.network.interceptor.LoggingInterceptor
import com.finderbar.hilsa.core.network.interceptor.TimingInterceptor
import okhttp3.OkHttpClient

/**
 * Author: Ko Thein (Nathan Mratt)
 * Created: 7/4/2026
 * Project: Hilsa
 */
object OkHttpFactory {

    fun create(
        authInterceptor: AuthInterceptor,
        loggingInterceptor: LoggingInterceptor,
        timingInterceptor: TimingInterceptor
    ): OkHttpClient {

        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(timingInterceptor)
            .build()
    }
}