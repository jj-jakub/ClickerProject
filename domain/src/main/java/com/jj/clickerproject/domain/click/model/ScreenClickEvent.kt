package com.jj.clickerproject.domain.click.model

sealed interface ScreenClickEvent {
    data object BackClick : ScreenClickEvent
    data object HomeClick : ScreenClickEvent
    data object RecentAppsClick: ScreenClickEvent
    data class Touch(
        val xClickCoordinate: Float,
        val yClickCoordinate: Float,
    ) : ScreenClickEvent
}