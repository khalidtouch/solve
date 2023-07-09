package com.khalidtouch.solve

import com.khalidtouch.solve.domain.Operation
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
        viewModel.enterNumber(23344445)
        viewModel.enterNumber(4242)
        assertEquals("23344445", viewModel.state.number1)
        assertNotEquals("4242", viewModel.state.number2)
    }

    @Test
    fun enterNumber_whenNoOperation_updateNumber1State() {
        viewModel.enterNumber(34)
        assertEquals("34", viewModel.state.number1)
        assertEquals("", viewModel.state.number2)
    }

    @Test
    fun enterNumber_whenOperation_updateNumber2State() {
        viewModel.enterNumber(12)
        viewModel.enterOperation(Operation.Add)
        viewModel.enterNumber(23)
        assertEquals("12", viewModel.state.number1)
        assertEquals("23", viewModel.state.number2)
    }


    @Test
    fun enterDecimal_whenNoOpAndNumber1HasNoDecimal_addDecimal() {
        viewModel.enterNumber(23)
        viewModel.enterDecimal()
        assertEquals("23.", viewModel.state.number1)
        assertEquals("", viewModel.state.number2)
    }

    @Test
    fun enterDecimal_whenNoOpAndNumber1HasDecimal_ignoreDecimal() {
        viewModel.enterNumber(46)
        viewModel.enterDecimal()
        viewModel.enterNumber(3)
        viewModel.enterDecimal()
        assertEquals("46.3", viewModel.state.number1)
        assertEquals("", viewModel.state.number2)
    }


    @Test
    fun enterDecimal_whenNoOpAndNumber1IsBlank_ignoreDecimal() {
        viewModel.enterDecimal()
        assertEquals("", viewModel.state.number1)
        assertEquals("", viewModel.state.number2)
    }

    @Test
    fun enterDecimal_whenOpAndNumber2HasNoDecimal_addDecimal() {
        viewModel.enterNumber(23)
        viewModel.enterOperation(Operation.Subtract)
        viewModel.enterNumber(34)
        viewModel.enterDecimal()
        assertEquals("23", viewModel.state.number1)
        assertEquals("34.", viewModel.state.number2)
    }

    @Test
    fun enterDecimal_whenOpAndNumber2HasDecimal_ignoreDecimal() {
        viewModel.enterNumber(90)
        viewModel.enterOperation(Operation.Divide)
        viewModel.enterNumber(12)
        viewModel.enterDecimal()
        viewModel.enterNumber(6)
        viewModel.enterDecimal()
        assertEquals("90", viewModel.state.number1)
        assertEquals("12.6", viewModel.state.number2)
    }

    @Test
    fun enterDecimal_whenOpAndNumber2IsBlank_ignoreDecimal() {
        viewModel.enterNumber(90)
        viewModel.enterOperation(Operation.Divide)
        viewModel.enterDecimal()
        assertEquals("90", viewModel.state.number1)
        assertEquals("", viewModel.state.number2)
    }

    @Test
    fun calculate_whenNumber1Blank_doNothing() {
        viewModel.calculate()
        assertEquals("", viewModel.state.number1)
        assertEquals("", viewModel.state.number2)
    }

    @Test
    fun calculate_whenNumber2Blank_doNothing() {
        viewModel.enterNumber(45)
        viewModel.enterOperation(Operation.Add)
        viewModel.calculate()
        assertEquals("45", viewModel.state.number1)
        assertEquals("", viewModel.state.number2)
        assertEquals(Operation.Add, viewModel.state.op)
    }

    @Test
    fun calculate_whenNumber1AndNumber2ValidAndOpAdd_performAddition() {
        viewModel.enterNumber(5)
        viewModel.enterOperation(Operation.Add)
        viewModel.enterNumber(6)
        viewModel.calculate()
        assertEquals("11.0", viewModel.state.number1)
        assertEquals("", viewModel.state.number2)
        assertEquals(null, viewModel.state.op)
    }

    @Test
    fun calculate_whenNumber1AndNumber2ValidAndOpSubtract_performSubtraction() {
        viewModel.enterNumber(7)
        viewModel.enterOperation(Operation.Subtract)
        viewModel.enterNumber(6)
        viewModel.calculate()
        assertEquals("1.0", viewModel.state.number1)
        assertEquals("", viewModel.state.number2)
        assertEquals(null, viewModel.state.op)
    }

    @Test
    fun calculate_whenNumber1AndNumber2ValidAndOpDivide_performDivision() {
        viewModel.enterNumber(25)
        viewModel.enterOperation(Operation.Divide)
        viewModel.enterNumber(5)
        viewModel.calculate()
        assertEquals("5.0", viewModel.state.number1)
        assertEquals("", viewModel.state.number2)
        assertEquals(null, viewModel.state.op)
    }

    @Test
    fun calculate_whenNumber1AndNumber2ValidAndOpTimes_performMultiplication() {
        viewModel.enterNumber(5)
        viewModel.enterOperation(Operation.Multiply)
        viewModel.enterNumber(6)
        viewModel.calculate()
        assertEquals("30.0", viewModel.state.number1)
        assertEquals("", viewModel.state.number2)
        assertEquals(null, viewModel.state.op)
    }

    @Test
    fun delete_whenNumber2IsNotBlank_performDelete() {
        viewModel.enterNumber(12)
        viewModel.enterOperation(Operation.Add)
        viewModel.enterNumber(67)
        viewModel.delete()
        assertEquals("12", viewModel.state.number1)
        assertEquals(Operation.Add, viewModel.state.op)
        assertEquals("6", viewModel.state.number2)
    }

    @Test
    fun delete_whenOp_performDelete() {
        viewModel.enterNumber(12)
        viewModel.enterOperation(Operation.Add)
        viewModel.delete()
        assertEquals("12", viewModel.state.number1)
        assertEquals(null, viewModel.state.op)
        assertEquals("", viewModel.state.number2)
    }

    @Test
    fun delete_whenNumber1IsNotBlank_performDelete() {
        viewModel.enterNumber(45)
        viewModel.delete()
        assertEquals("4", viewModel.state.number1)
        assertEquals(null, viewModel.state.op)
        assertEquals("", viewModel.state.number2)
    }
}


