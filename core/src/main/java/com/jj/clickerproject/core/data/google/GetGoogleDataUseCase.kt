package com.jj.clickerproject.core.data.google

import com.jj.clickerproject.domain.google.ClickerRepository

class GetGoogleDataUseCase(
    private val clickerRepository: ClickerRepository,
) {
    suspend operator fun invoke() = clickerRepository.getGoogleData()
}