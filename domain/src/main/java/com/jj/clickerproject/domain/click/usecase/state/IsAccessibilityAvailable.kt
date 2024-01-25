package com.jj.clickerproject.domain.click.usecase.state

import com.jj.clickerproject.domain.click.AccessibilityClickRepository

class IsAccessibilityAvailable(
    private val accessibilityClickRepository: AccessibilityClickRepository,
) {
    operator fun invoke(): Boolean =
        accessibilityClickRepository.observeAccessibilityClickAvailability().value
}