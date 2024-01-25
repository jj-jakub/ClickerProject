package com.jj.clickerproject.data.click

import com.jj.clickerproject.domain.click.AccessibilityClickRepository
import com.jj.clickerproject.domain.click.model.ClickSequence
import com.jj.clickerproject.domain.click.model.ClickSequenceEvent
import com.jj.clickerproject.domain.click.model.ScreenClickEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DefaultAccessibilityClickRepository : AccessibilityClickRepository {

    private val _isRemoteControlAvailable = MutableStateFlow(false)

    override fun observeAccessibilityClickAvailability() = _isRemoteControlAvailable.asStateFlow()

    override fun setAccessibilityClickAvailability(isAvailable: Boolean) {
        _isRemoteControlAvailable.value = isAvailable
    }

    override fun getDefaultClickSequence(): ClickSequence {
        return ClickSequence(
            repeatTimes = 5,
            events = listOf(
                ClickSequenceEvent.Click(ScreenClickEvent.Touch(355.0F, 1422.0F, 300L)),
                ClickSequenceEvent.Delay(200L + 300L),
                ClickSequenceEvent.Click(ScreenClickEvent.Touch(725.0F, 1422.0F, 600L)),
                ClickSequenceEvent.Delay(200L + 600L),
            ),
        )
    }
}