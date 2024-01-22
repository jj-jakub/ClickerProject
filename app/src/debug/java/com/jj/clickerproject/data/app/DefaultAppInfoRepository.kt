package com.jj.clickerproject.data.app

import android.content.Context
import com.jj.clickerproject.domain.app.AppInfoRepository

class DefaultAppInfoRepository(
    private val context: Context,
) : AppInfoRepository {
    override suspend fun installedFromValidSource(): Boolean = true
}