package com.jj.clickerproject.data.google.network

import com.jj.clickerproject.data.google.service.ClickerService
import com.jj.clickerproject.data.utils.NetworkException
import com.jj.clickerproject.data.utils.toResult
import com.jj.clickerproject.domain.BaseResult

class ClickerNetwork(
    private val clickerService: ClickerService,
) {

    suspend fun getGoogleData(): BaseResult<String> {
        val result = clickerService.getGoogleData()
        return if (result.isSuccessful) {
            BaseResult.Success(result.code().toString())
        } else {
            BaseResult.Error(NetworkException(result.code(), result.message()))
        }
    }

    suspend fun getGoogleStatus(): BaseResult<Unit> = clickerService.getGoogleData().toResult()
}