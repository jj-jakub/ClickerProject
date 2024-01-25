package com.jj.clickerproject.data.click

import com.jj.clickerproject.domain.click.AccessibilityClickRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DefaultAccessibilityClickRepository : AccessibilityClickRepository {

    private val _isRemoteControlAvailable = MutableStateFlow(false)

    override fun observeAccessibilityClickAvailability() = _isRemoteControlAvailable.asStateFlow()

    override fun setAccessibilityClickAvailability(isAvailable: Boolean) {
        _isRemoteControlAvailable.value = isAvailable
    }
}