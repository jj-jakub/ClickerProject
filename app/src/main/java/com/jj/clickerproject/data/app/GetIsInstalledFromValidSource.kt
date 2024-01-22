package com.jj.clickerproject.data.app

import com.jj.clickerproject.domain.app.AppInfoRepository

class GetIsInstalledFromValidSource(
    private val appInfoRepository: AppInfoRepository,
) {
    suspend operator fun invoke() = appInfoRepository.installedFromValidSource()
}