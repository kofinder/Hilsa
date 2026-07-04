package com.finderbar.hilsa.di

import com.finderbar.hilsa.core.common.Constants
import com.finderbar.hilsa.core.network.api.AuthApi
import com.finderbar.hilsa.core.network.contract.RoomTokenProvider
import com.finderbar.hilsa.core.network.contract.TokenProvider
import com.finderbar.hilsa.core.network.interceptor.AuthInterceptor
import com.finderbar.hilsa.core.network.interceptor.LoggingInterceptor
import com.finderbar.hilsa.core.network.interceptor.TimingInterceptor
import com.finderbar.hilsa.core.network.retrofit.OkHttpFactory
import com.finderbar.hilsa.core.network.retrofit.RetrofitFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Binds
    @Singleton
    abstract fun bindTokenProvider(roomTokenProvider: RoomTokenProvider): TokenProvider

    companion object {

        @Provides
        @Singleton
        fun provideLoggingInterceptor(): LoggingInterceptor = LoggingInterceptor()

        @Provides
        @Singleton
        fun provideTimingInterceptor(): TimingInterceptor = TimingInterceptor()

        @Provides
        @Singleton
        fun provideAuthInterceptor(tokenProvider: TokenProvider): AuthInterceptor = 
            AuthInterceptor(tokenProvider)

        @Provides
        @Singleton
        fun provideOkHttpClient(
            authInterceptor: AuthInterceptor,
            loggingInterceptor: LoggingInterceptor,
            timingInterceptor: TimingInterceptor
        ): OkHttpClient {
            return OkHttpFactory.create(
                authInterceptor,
                loggingInterceptor,
                timingInterceptor
            )
        }

        @Provides
        @Singleton
        fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
            return RetrofitFactory.create(
                okHttpClient,
                Constants.BASE_URL
            )
        }

        @Provides
        @Singleton
        fun provideAuthApi(retrofit: Retrofit): AuthApi {
            return retrofit.create(AuthApi::class.java)
        }
    }
}