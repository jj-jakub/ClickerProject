package com.jj.clickerproject.domain.click

import com.jj.clickerproject.domain.click.model.ClickSequence
import kotlinx.coroutines.flow.StateFlow

interface AccessibilityClickRepository {
    fun observeAccessibilityClickAvailability(): StateFlow<Boolean>
    fun setAccessibilityClickAvailability(isAvailable: Boolean)
    fun observeClickSequenceRunning(): StateFlow<Boolean>
    fun setClickSequenceRunning(isRunning: Boolean)
    fun getDefaultClickSequence(): ClickSequence
}