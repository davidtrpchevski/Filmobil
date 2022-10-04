package com.david.filmobil.network.result

import retrofit2.Response

suspend fun <T> unpackResult(handleCall: suspend () -> Response<T>): ApiResult<T> =
    try {
        val call = handleCall()
        if (call.isSuccessful) {
            val data = call.body()
            if (data == null) {
                ApiResult.Error(NullPointerException())
            } else {
                ApiResult.Success(data)
            }
        } else {
            ApiResult.ApiError(call.errorBody(), call.code())
        }
    } catch (throwable: Throwable) {
        ApiResult.Error(throwable)
    }

fun <T> ApiResult<T>.getResultAsSuccess(): T? = if (this is ApiResult.Success) data else null
