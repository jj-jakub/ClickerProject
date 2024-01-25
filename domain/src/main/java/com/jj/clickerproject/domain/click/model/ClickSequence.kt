package com.jj.clickerproject.domain.click.model

data class ClickSequence(
    val repeatTimes: Int,
    val events: List<ClickSequenceEvent>,
)
