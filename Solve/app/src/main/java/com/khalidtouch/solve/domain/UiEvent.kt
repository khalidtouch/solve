package com.khalidtouch.solve.domain

sealed class UiEvent {
    data class Number(val number: Int) : UiEvent()
    object Clear : UiEvent()
    object Delete : UiEvent()
    data class Op(val operation: Operation) : UiEvent()
    object Calculate : UiEvent()
    object Decimal : UiEvent()
}
