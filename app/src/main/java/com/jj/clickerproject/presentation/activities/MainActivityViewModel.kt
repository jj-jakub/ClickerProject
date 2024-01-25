package com.jj.clickerproject.presentation.activities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jj.clickerproject.domain.click.AccessibilityClickRepository
import com.jj.clickerproject.domain.click.RunClickSequenceManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val runClickSequenceManager: RunClickSequenceManager,
    private val accessibilityClickRepository: AccessibilityClickRepository,
) : ViewModel() {

    private val _viewState =
        MutableStateFlow(
            MainViewState(
                isClickSequenceRunning = false,
            )
        )

    val viewState = _viewState.asStateFlow()

    init {
        viewModelScope.launch {
            accessibilityClickRepository.observeClickSequenceRunning().collect { isRunning ->
                _viewState.value = viewState.value.copy(
                    isClickSequenceRunning = isRunning,
                )
            }
        }
    }

    fun onStartClicking() {
        runClickSequenceManager.start()
    }

    fun onStopClicking() {
        runClickSequenceManager.stop()
    }
}