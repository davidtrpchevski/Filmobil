package com.david.filmobil.network.result

import retrofit2.Response

fun <T> Response<T>.unpackResult(): ApiResult<T> =
    if (isSuccessful) {
        val data = body()
        if (data == null) {
            ApiResult.Error(NullPointerException())
        } else {
            ApiResult.Success(data)
        }
    } else {
        ApiResult.ApiError(errorBody(), code())
    }

fun <T> ApiResult<T>.getResultAsSuccess(): T? = if (this is ApiResult.Success) data else null
