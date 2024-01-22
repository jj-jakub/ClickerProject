package com.jj.clickerproject.presentation

import androidx.lifecycle.ViewModel
import com.jj.clickerproject.data.ad.GetMainAdUnitId
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainRootViewModel(
    getMainAdUnitId: GetMainAdUnitId,
) : ViewModel() {

    private val _viewState =
        MutableStateFlow(
            MainRootViewState(
                adMainUnitId = getMainAdUnitId(),
            )
        )
    val viewState = _viewState.asStateFlow()

    fun onAdClicked() {
        /* no-op */
    }
}