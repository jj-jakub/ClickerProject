package com.jj.clickerproject.domain.click

class ObserveAccessibilityClickAvailability(
    private val accessibilityClickRepository: AccessibilityClickRepository,
) {
    operator fun invoke() = accessibilityClickRepository.observeAccessibilityClickAvailability()
}