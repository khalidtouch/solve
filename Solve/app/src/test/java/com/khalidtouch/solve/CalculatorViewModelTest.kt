package com.khalidtouch.solve

import com.khalidtouch.solve.domain.Operation
import com.khalidtouch.solve.domain.UiEvent
import com.khalidtouch.solve.ui.CalculatorViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test

class CalculatorViewModelTest {
    lateinit var viewModel: CalculatorViewModel

    @Before
    fun setup() {
        viewModel = CalculatorViewModel()
    }

    @Test
    fun enterNumber_whenLengthOfNumberTooLong_doesNothing() {
        viewModel.onEvent(UiEvent.Number(23344445))
        viewModel.onEvent(UiEvent.Number(4242))
        assertEquals("23344445", viewModel.state.number1)
        assertNotEquals("4242", viewModel.state.number2)

        viewModel.onEvent(UiEvent.Op(Operation.Add))
        viewModel.onEvent(UiEvent.Number(12345678))
        viewModel.onEvent(UiEvent.Number(3423))

        assertEquals("12345678", viewModel.state.number2)
    }

    @Test
    fun enterNumber_whenNoOperation_updateNumber1State() {
        viewModel.onEvent(UiEvent.Number(34))
        assertEquals("34", viewModel.state.number1)
        assertEquals("", viewModel.state.number2)

        viewModel.onEvent(UiEvent.Number(68))
        assertEquals("3468", viewModel.state.number1)
        assertEquals("", viewModel.state.number2)
    }

    @Test
    fun enterOperation_whenOpEnteredAndNumber1IsBlank_opIsNull() {
        viewModel.onEvent(UiEvent.Op(Operation.Add))
        assertEquals(null, viewModel.state.op)
        assertEquals("", viewModel.state.number1)
    }

    @Test
    fun enterOperation_whenOpEnteredAndNumber1IsNotBlank_updateOp() {
        viewModel.onEvent(UiEvent.Number(458))
        viewModel.onEvent(UiEvent.Op(Operation.Add))

        assertEquals(Operation.Add, viewModel.state.op)
        assertEquals("458", viewModel.state.number1)
    }

    @Test
    fun enterOperation_whenOpEnteredAndNumber2IsNotBlank_calculateThenUpdateOp() {
        viewModel.onEvent(UiEvent.Number(23))
        viewModel.onEvent(UiEvent.Op(Operation.Add))
        viewModel.onEvent(UiEvent.Number(10))
        viewModel.onEvent(UiEvent.Op(Operation.Subtract))

        assertEquals("33.0", viewModel.state.number1)
        assertEquals(Operation.Subtract, viewModel.state.op)
        assertEquals("", viewModel.state.number2)

        viewModel.onEvent(UiEvent.Number(20))
        assertEquals("20", viewModel.state.number2)

        viewModel.onEvent(UiEvent.Op(Operation.Multiply))
        viewModel.onEvent(UiEvent.Number(2))

        assertEquals("13.0", viewModel.state.number1)
        assertEquals(Operation.Multiply, viewModel.state.op)
        assertEquals("2", viewModel.state.number2)

        viewModel.onEvent(UiEvent.Calculate)
        assertEquals("26.0", viewModel.state.number1)
        assertEquals("", viewModel.state.number2)

    }

    @Test
    fun enterNumber_whenOperation_updateNumber2State() {
        viewModel.onEvent(UiEvent.Number(12))
        viewModel.onEvent(UiEvent.Op(Operation.Add))
        viewModel.onEvent(UiEvent.Number(23))
        assertEquals("12", viewModel.state.number1)
        assertEquals("23", viewModel.state.number2)

        viewModel.onEvent(UiEvent.Number(900))
        assertEquals("12", viewModel.state.number1)
        assertEquals("23900", viewModel.state.number2)
    }


    @Test
    fun enterDecimal_whenNoOpAndNumber1HasNoDecimal_addDecimal() {
        viewModel.onEvent(UiEvent.Number(23))
        viewModel.onEvent(UiEvent.Decimal)
        assertEquals("23.", viewModel.state.number1)
        assertEquals("", viewModel.state.number2)
    }

    @Test
    fun enterDecimal_whenNoOpAndNumber1HasDecimal_ignoreDecimal() {
        viewModel.onEvent(UiEvent.Number(46))
        viewModel.onEvent(UiEvent.Decimal)
        viewModel.onEvent(UiEvent.Number(3))
        viewModel.onEvent(UiEvent.Decimal)
        assertEquals("46.3", viewModel.state.number1)
        assertEquals("", viewModel.state.number2)
    }


    @Test
    fun enterDecimal_whenNoOpAndNumber1IsBlank_appendZeroAndDecimal() {
        viewModel.onEvent(UiEvent.Decimal)
        assertEquals("0.", viewModel.state.number1)
        assertEquals("", viewModel.state.number2)
    }

    @Test
    fun enterDecimal_whenOpAndNumber2HasNoDecimal_addDecimal() {
        viewModel.onEvent(UiEvent.Number(23))
        viewModel.onEvent(UiEvent.Op(Operation.Subtract))
        viewModel.onEvent(UiEvent.Number(34))
        viewModel.onEvent(UiEvent.Decimal)
        assertEquals("23", viewModel.state.number1)
        assertEquals("34.", viewModel.state.number2)
    }

    @Test
    fun enterDecimal_whenOpAndNumber2HasDecimal_ignoreDecimal() {
        viewModel.onEvent(UiEvent.Number(90))
        viewModel.onEvent(UiEvent.Op(Operation.Divide))
        viewModel.onEvent(UiEvent.Number(12))
        viewModel.onEvent(UiEvent.Decimal)
        viewModel.onEvent(UiEvent.Number(6))
        viewModel.onEvent(UiEvent.Decimal)
        assertEquals("90", viewModel.state.number1)
        assertEquals("12.6", viewModel.state.number2)
    }

    @Test
    fun enterDecimal_whenOpAndNumber2IsBlank_appendZeroAndDecimal() {
        viewModel.onEvent(UiEvent.Number(90))
        viewModel.onEvent(UiEvent.Op(Operation.Divide))
        viewModel.onEvent(UiEvent.Decimal)
        assertEquals("90", viewModel.state.number1)
        assertEquals(Operation.Divide, viewModel.state.op)
        assertEquals("0.", viewModel.state.number2)
    }

    @Test
    fun calculate_whenNumber1Blank_doNothing() {
        viewModel.onEvent(UiEvent.Calculate)
        assertEquals("", viewModel.state.number1)
        assertEquals("", viewModel.state.number2)
    }

    @Test
    fun calculate_whenNumber2Blank_doNothing() {
        viewModel.onEvent(UiEvent.Number(45))
        viewModel.onEvent(UiEvent.Op(Operation.Add))
        viewModel.onEvent(UiEvent.Calculate)
        assertEquals("45", viewModel.state.number1)
        assertEquals("", viewModel.state.number2)
        assertEquals(Operation.Add, viewModel.state.op)
    }

    @Test
    fun calculate_whenNumber1AndNumber2ValidAndOpAdd_performAddition() {
        viewModel.onEvent(UiEvent.Number(5))
        viewModel.onEvent(UiEvent.Op(Operation.Add))
        viewModel.onEvent(UiEvent.Number(6))
        viewModel.onEvent(UiEvent.Calculate)
        assertEquals("11.0", viewModel.state.number1)
        assertEquals("", viewModel.state.number2)
        assertEquals(null, viewModel.state.op)
    }

    @Test
    fun calculate_whenNumber1AndNumber2ValidAndOpSubtract_performSubtraction() {
        viewModel.onEvent(UiEvent.Number(7))
        viewModel.onEvent(UiEvent.Op(Operation.Subtract))
        viewModel.onEvent(UiEvent.Number(6))
        viewModel.onEvent(UiEvent.Calculate)
        assertEquals("1.0", viewModel.state.number1)
        assertEquals("", viewModel.state.number2)
        assertEquals(null, viewModel.state.op)
    }

    @Test
    fun calculate_whenNumber1AndNumber2ValidAndOpDivide_performDivision() {
        viewModel.onEvent(UiEvent.Number(25))
        viewModel.onEvent(UiEvent.Op(Operation.Divide))
        viewModel.onEvent(UiEvent.Number(5))
        viewModel.onEvent(UiEvent.Calculate)
        assertEquals("5.0", viewModel.state.number1)
        assertEquals("", viewModel.state.number2)
        assertEquals(null, viewModel.state.op)
    }

    @Test
    fun calculate_whenNumber1AndNumber2ValidAndOpTimes_performMultiplication() {
        viewModel.onEvent(UiEvent.Number(5))
        viewModel.onEvent(UiEvent.Op(Operation.Multiply))
        viewModel.onEvent(UiEvent.Number(6))
        viewModel.onEvent(UiEvent.Calculate)
        assertEquals("30.0", viewModel.state.number1)
        assertEquals("", viewModel.state.number2)
        assertEquals(null, viewModel.state.op)
    }

    @Test
    fun delete_whenNumber2IsNotBlank_performDelete() {
        viewModel.onEvent(UiEvent.Number(12))
        viewModel.onEvent(UiEvent.Op(Operation.Add))
        viewModel.onEvent(UiEvent.Number(67))
        viewModel.onEvent(UiEvent.Delete)
        assertEquals("12", viewModel.state.number1)
        assertEquals(Operation.Add, viewModel.state.op)
        assertEquals("6", viewModel.state.number2)
    }

    @Test
    fun delete_whenOp_performDelete() {
        viewModel.onEvent(UiEvent.Number(12))
        viewModel.onEvent(UiEvent.Op(Operation.Add))
        viewModel.onEvent(UiEvent.Delete)
        assertEquals("12", viewModel.state.number1)
        assertEquals(null, viewModel.state.op)
        assertEquals("", viewModel.state.number2)
    }

    @Test
    fun delete_whenNumber1IsNotBlank_performDelete() {
        viewModel.onEvent(UiEvent.Number(45))
        viewModel.onEvent(UiEvent.Delete)
        assertEquals("4", viewModel.state.number1)
        assertEquals(null, viewModel.state.op)
        assertEquals("", viewModel.state.number2)
    }
}


