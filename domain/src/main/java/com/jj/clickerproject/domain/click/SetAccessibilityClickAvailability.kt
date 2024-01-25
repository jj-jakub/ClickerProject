package com.jj.clickerproject.domain.click

class SetAccessibilityClickAvailability(
    private val accessibilityClickRepository: AccessibilityClickRepository,
) {
    operator fun invoke(isAvailable: Boolean) =
        accessibilityClickRepository.setAccessibilityClickAvailability(isAvailable = isAvailable)
}