package com.khalidtouch.solve.domain

sealed class Action {
    data class Number(val number: Int) : Action()
    object Clear : Action()
    object Delete : Action()
    data class Op(val operation: Operation) : Action()
    object Calculate : Action()
    object Decimal : Action()
}