package com.madslangkilde.ui_base.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<T>(initialUIState: T) : ViewModel() {

    private val uiStateM: MutableStateFlow<T> = MutableStateFlow(initialUIState)
    val uiState: StateFlow<T>
        get() = uiStateM

    private val eventsM = MutableSharedFlow<UiEvent>(replay = 0, extraBufferCapacity = 1)
    val events: MutableSharedFlow<UiEvent>
        get() = eventsM

    protected fun updateUiState(callback: (T) -> T) {
        viewModelScope.launch(Dispatchers.Main) {
            uiStateM.value?.let { value ->
                callback(value)?.let {
                    uiStateM.value = it
                }
            }
        }
    }

    private var debounceJob: Job? = null

    protected fun updateUiState(debounce: Long = 0L, callback: (T) -> T) {
        viewModelScope.launch(Dispatchers.Main) {
            debounceJob?.cancel()
            uiStateM.value?.let { value ->
                debounceJob = viewModelScope.launch {
                    delay(debounce)
                    callback(value)?.let {
                        uiStateM.value = it
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    protected suspend fun sendEvent(event: UiEvent) {
        eventsM.emit(event)
        eventsM.resetReplayCache()
    }
}