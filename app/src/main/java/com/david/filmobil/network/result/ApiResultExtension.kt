package com.david.filmobil.network.result

import retrofit2.Response

fun <T> Response<T>.unpackResult() =
    if (isSuccessful) {
        val result = body()
        if (result == null) {
            ApiResult.Error(NullPointerException())
        } else {
            ApiResult.Success(result)
        }
    } else {
        ApiResult.ApiError(errorBody(), code())
    }

fun <T> ApiResult<T>.getResultAsSuccess(): T? = if (this is ApiResult.Success) data else null
