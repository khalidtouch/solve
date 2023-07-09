package com.khalidtouch.solve.domain

interface OnChangeState {
    fun enterOperation(op: Operation)

    fun calculate()

    fun delete()

    fun enterDecimal()

    fun enterNumber(number: Int)

    fun onAction(action: Action)
}