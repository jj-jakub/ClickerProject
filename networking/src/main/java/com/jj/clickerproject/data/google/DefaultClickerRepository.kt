package com.jj.clickerproject.data.google

import com.jj.clickerproject.data.google.network.ClickerNetwork
import com.jj.clickerproject.domain.google.ClickerRepository

class DefaultClickerRepository(
    private val clickerNetwork: ClickerNetwork,
) : ClickerRepository {

    override suspend fun getGoogleData() = clickerNetwork.getGoogleData()

    override suspend fun getGoogleStatus() = clickerNetwork.getGoogleStatus()
}