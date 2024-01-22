package com.jj.clickerproject.domain.google

import com.jj.clickerproject.domain.BaseResult

interface ClickerRepository {
    suspend fun getGoogleData(): BaseResult<String>
    suspend fun getGoogleStatus(): BaseResult<Unit>
}