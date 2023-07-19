package com.khalidtouch.solve.domain

interface EventHandler {
    fun onEvent(event: UiEvent)
}