package com.jj.clickerproject.domain.click

import kotlinx.coroutines.flow.StateFlow

interface AccessibilityClickRepository {
    fun observeAccessibilityClickAvailability(): StateFlow<Boolean>
    fun setAccessibilityClickAvailability(isAvailable: Boolean)
}