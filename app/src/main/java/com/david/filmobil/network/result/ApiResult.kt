package com.david.filmobil.network.result

import okhttp3.ResponseBody

sealed class ApiResult<out T> {
    object Initial : ApiResult<Nothing>()
    object Loading : ApiResult<Nothing>()
    data class Success<T>(val data: T) : ApiResult<T>()
    data class ApiError(val responseBody: ResponseBody?, val errorCode: Int) : ApiResult<Nothing>()
    data class Error(val throwable: Throwable) : ApiResult<Nothing>()
}
