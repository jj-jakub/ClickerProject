package com.jj.clickerproject.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jj.clickerproject.domain.click.ClickManager
import com.jj.clickerproject.domain.click.RunClickSequenceManager
import com.jj.clickerproject.domain.click.usecase.state.ObserveAccessibilityClickAvailability
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

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