package com.finderbar.hilsa.core.network.call

import com.finderbar.hilsa.core.network.model.NetworkException
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

/**
 * Author: Ko Thein (Nathan Mratt)
 * Created: 7/4/2026
 * Project: Hilsa
 */
object SafeApiCall {

    suspend fun <T> execute(
        apiCall: suspend () -> T
    ): ApiResult<T> {

        return try {
            val result = apiCall()
            ApiResult.Success(result)
        } catch (e: SocketTimeoutException) {
            ApiResult.Error(NetworkException.Timeout)
        } catch (e: IOException) {
            ApiResult.Error(NetworkException.NoInternet)
        } catch (e: HttpException) {
            ApiResult.Error(
                NetworkException.HttpError(
                    code = e.code(),
                    message = e.message()
                )
            )
        } catch (e: Throwable) {
            ApiResult.Error(NetworkException.Unknown(e))
        }
    }
}