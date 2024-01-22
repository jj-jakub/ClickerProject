package com.jj.clickerproject.data.utils

import com.jj.clickerproject.domain.BaseResult
import retrofit2.Response

fun <T : Any> Response<T>.toResult(): BaseResult<T> {
    val body = body()
    return if (isSuccessful && body != null) {
        BaseResult.Success(body)
    } else {
        BaseResult.Error(NetworkException(code(), message()))
    }
}