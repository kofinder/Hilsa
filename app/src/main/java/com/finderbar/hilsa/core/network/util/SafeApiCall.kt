package com.finderbar.hilsa.core.network.util

import com.finderbar.hilsa.core.common.Result
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

/**
 * A utility object to safely execute network requests and catch common exceptions.
 */
object SafeApiCall {

    /**
     * Executes a suspendable network call and wraps the result in a [Result].
     *
     * @param apiCall The suspendable network request block.
     * @return [Result.Success] if the call succeeds, or [Result.Error] if an exception occurs.
     */
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
