package com.jj.clickerproject.domain.click.usecase

import com.jj.clickerproject.domain.click.AccessibilityClickRepository
import com.jj.clickerproject.domain.click.ClickManager
import com.jj.clickerproject.domain.click.model.ClickSequenceEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PerformClickLoop(
    private val clickRepository: AccessibilityClickRepository,
    private val clickManager: ClickManager,
) {

    private var clickJob: Job? = null

    operator fun invoke() {
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
        }
    }
}