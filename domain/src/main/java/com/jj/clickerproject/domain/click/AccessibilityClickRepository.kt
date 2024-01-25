package com.jj.clickerproject.domain.click

import com.jj.clickerproject.domain.click.model.ClickSequence
import kotlinx.coroutines.flow.StateFlow

interface AccessibilityClickRepository {
    fun observeAccessibilityClickAvailability(): StateFlow<Boolean>
    fun setAccessibilityClickAvailability(isAvailable: Boolean)
    fun getDefaultClickSequence(): ClickSequence
}