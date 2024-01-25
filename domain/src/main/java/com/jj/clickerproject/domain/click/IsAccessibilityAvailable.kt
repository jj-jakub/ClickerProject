package com.jj.clickerproject.domain.click

class IsAccessibilityAvailable(
    private val accessibilityClickRepository: AccessibilityClickRepository,
) {
    operator fun invoke(): Boolean =
        accessibilityClickRepository.observeAccessibilityClickAvailability().value
}