package com.finderbar.hilsa.core.network.util

import com.finderbar.hilsa.core.common.Result
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

object SafeApiCall {

    suspend fun <T> execute(
        apiCall: suspend () -> T
    ): Result<T> {

        return try {
            val result = apiCall()
            Result.Success(result)
        } catch (e: SocketTimeoutException) {
            Result.Error(NetworkException.Timeout)
        } catch (e: IOException) {
            Result.Error(NetworkException.NoInternet)
        } catch (e: HttpException) {
            Result.Error(
                NetworkException.HttpError(
                    code = e.code(),
                    message = e.message()
                )
            )
        } catch (e: Throwable) {
            Result.Error(NetworkException.Unknown(e))
        }
    }
}