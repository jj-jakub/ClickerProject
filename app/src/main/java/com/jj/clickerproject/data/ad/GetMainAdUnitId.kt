package com.jj.clickerproject.data.ad

import com.jj.clickerproject.BuildConfig

class GetMainAdUnitId {
    operator fun invoke(): String = BuildConfig.adMainBannerViewAdUnitId
}