package com.jj.clickerproject.domain.ad

interface AdManager {
    fun initAds()
    fun incrementActionsForAd()
    fun showInterstitialAd()
}