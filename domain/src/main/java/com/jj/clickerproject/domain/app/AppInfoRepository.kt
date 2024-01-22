package com.jj.clickerproject.domain.app

interface AppInfoRepository {
    suspend fun installedFromValidSource(): Boolean
}