package com.jj.clickerproject.domain.click.usecase.state

import com.jj.clickerproject.domain.click.AccessibilityClickRepository

class SetAccessibilityClickAvailability(
    private val accessibilityClickRepository: AccessibilityClickRepository,
) {
    operator fun invoke(isAvailable: Boolean) =
        accessibilityClickRepository.setAccessibilityClickAvailability(isAvailable = isAvailable)
}