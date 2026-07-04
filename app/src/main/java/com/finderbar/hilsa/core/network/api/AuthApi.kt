package com.finderbar.hilsa.core.network.api

import com.finderbar.hilsa.core.network.model.LoginRequest
import com.finderbar.hilsa.core.network.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse
}