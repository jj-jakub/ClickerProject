package com.jj.clickerproject.presentation

import androidx.lifecycle.ViewModel
import com.jj.clickerproject.domain.click.ClickManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainRootViewModel(
    clickManager: ClickManager,
) : ViewModel() {

    private val _viewState =
        MutableStateFlow(
            MainRootViewState(
                adMainUnitId = "",
            )
        )
    val viewState = _viewState.asStateFlow()

    init {
        clickManager.start()
    }
}