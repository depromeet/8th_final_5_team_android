package com.example.go_with_dog.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<E : Event> : ViewModel() {
    private val _event = MutableStateFlow(NoEvent)
    val event: StateFlow<Event> = _event
}