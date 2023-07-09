package com.khalidtouch.solve.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.khalidtouch.solve.domain.Action
import com.khalidtouch.solve.domain.OnChangeState
import com.khalidtouch.solve.domain.Operation

class CalculatorViewModel : ViewModel(), OnChangeState {

    var state by mutableStateOf(CalculatorState())

    companion object {
        private const val MAX_NUM_LENGTH = 8
    }

    override fun enterOperation(op: Operation) {
        if (state.number1.isBlank()) return
        state = state.copy(op = op)
    }

    override fun calculate() {
        val number1 = state.number1.toDoubleOrNull()
        val number2 = state.number2.toDoubleOrNull()
        if (number1 == null || number2 == null) return

        val result = when (state.op) {
            is Operation.Add -> number1 + number2
            is Operation.Subtract -> number1 - number2
            is Operation.Divide -> number1 / number2
            is Operation.Multiply -> number1 * number2
            null -> return
        }

        state = state.copy(
            number1 = result.toString().take(15),
            number2 = "",
            op = null,
        )
    }

    override fun delete() {
        when {
            state.number2.isNotBlank() -> state = state.copy(
                number2 = state.number2.dropLast(1)
            )

            state.op != null -> state = state.copy(
                op = null
            )

            state.number1.isNotBlank() -> state = state.copy(
                number1 = state.number1.dropLast(1)
            )
        }
    }

    override fun enterDecimal() {
        if (state.op == null) {
            if (state.number1.contains(".") || state.number1.isBlank()) return
            state = state.copy(number1 = state.number1 + ".")
            return
        }
        if (state.number2.contains(".") || state.number2.isBlank()) return
        state = state.copy(number2 = state.number2 + ".")
    }


    override fun enterNumber(number: Int) {
        if (state.op == null) {
            if (state.number1.length >= MAX_NUM_LENGTH) return
            state = state.copy(
                number1 = state.number1 + number
            )
            return
        }

        if (state.number2.length >= MAX_NUM_LENGTH) return
        state = state.copy(
            number2 = state.number2 + number
        )

    }

    override fun onAction(action: Action) {
       when(action) {
           is Action.Number -> enterNumber(action.number)
           is Action.Delete -> delete()
           is Action.Clear -> state = CalculatorState()
           is Action.Op -> enterOperation(action.operation)
           is Action.Decimal -> enterDecimal()
           is Action.Calculate -> calculate()
       }
    }
}

data class CalculatorState(
    val number1: String = "",
    val number2: String = "",
    val op: Operation? = null,
)