package com.jj.clickerproject.domain.click.model

sealed interface ClickSequenceEvent {
    data class Click(val event: ScreenClickEvent) : ClickSequenceEvent
    data class Delay(val delay: Long) : ClickSequenceEvent
}