package com.jj.clickerproject.domain.click.usecase.state

import com.jj.clickerproject.domain.click.AccessibilityClickRepository

class ObserveAccessibilityClickAvailability(
    private val accessibilityClickRepository: AccessibilityClickRepository,
) {
    operator fun invoke() = accessibilityClickRepository.observeAccessibilityClickAvailability()
}