package com.util.ktx.base

import com.util.ktx.base.basebean.Results
import com.util.ktx.base.basebean.WrapResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import java.io.IOException


open class BaseRepository {

    suspend fun <T : Any> apiCall(call: suspend () -> WrapResponse<T>): WrapResponse<T> {
        return call.invoke()
    }

    suspend fun <T : Any> safeApiCall(
        call: suspend () -> Results<T>,
        errorMessage: String
    ): Results<T> {
        return try {
            call()
        } catch (e: Exception) {
            // An exception was thrown when calling the API so we're converting this to an IOException
            Results.Error(
                IOException(
                    errorMessage,
                    e
                )
            )
        }
    }

    suspend fun <T : Any> executeResponse(
        response: WrapResponse<T>, successBlock: (suspend CoroutineScope.() -> Unit)? = null,
        errorBlock: (suspend CoroutineScope.() -> Unit)? = null
    ): Results<T> {
        return coroutineScope {
            if (response.errorCode == -1) {
                errorBlock?.let { it() }
                Results.Error(
                    IOException(
                        response.errorMsg
                    )
                )
            } else {
                successBlock?.let { it() }
                Results.Success(response.data)
            }
        }
    }


}