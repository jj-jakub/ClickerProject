package com.jj.clickerproject.domain.click

import com.jj.clickerproject.domain.click.model.ClickSequenceEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RunClickSequenceManager(
    private val clickRepository: AccessibilityClickRepository,
    private val clickManager: ClickManager,
) {

    private var clickJob: Job? = null

    fun start() {
        if (!clickRepository.observeAccessibilityClickAvailability().value) return
        clickRepository.setClickSequenceRunning(true)
        clickJob?.cancel()
        clickJob = CoroutineScope(Dispatchers.Default).launch {
            val sequence = clickRepository.getDefaultClickSequence()
            repeat(sequence.repeatTimes) {
                sequence.events.forEach {
                    when (it) {
                        is ClickSequenceEvent.Click -> clickManager.onEventReceived(it.event)
                        is ClickSequenceEvent.Delay -> delay(it.delay)
                    }
                }
            }
            clickRepository.setClickSequenceRunning(false)
        }
    }

    fun stop() {
        clickJob?.cancel()
        clickRepository.setClickSequenceRunning(false)
    }
}