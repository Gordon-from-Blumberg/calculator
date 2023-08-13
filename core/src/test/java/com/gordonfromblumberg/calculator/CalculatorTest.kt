package com.gordonfromblumberg.calculator

import com.gordonfromblumberg.calculator.Calculator.calculate
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CalculatorTest {
    @Test
    fun test() {
        Assertions.assertEquals(1f, calculate("1"))
        Assertions.assertEquals(-1f, calculate("-1"))
        Assertions.assertEquals(3f, calculate("1+2"))
        Assertions.assertEquals(-1f, calculate("1-2"))
        Assertions.assertEquals(6f, calculate("2*3"))
        Assertions.assertEquals(6f, calculate("1+2+3"))
        Assertions.assertEquals(5f, calculate("1*2+3"))
        Assertions.assertEquals(7f, calculate("1+2*3"))
        Assertions.assertEquals(2.5f, calculate("5/2"))
        Assertions.assertEquals(7f, calculate("1.5*3+5/2"))
        Assertions.assertEquals(3f, calculate("(1+2)"))
        Assertions.assertEquals(8f, calculate("(1+2)+5"))
        Assertions.assertEquals(3f, calculate("1-(1+2)+5"))
        Assertions.assertEquals(6f, calculate("3+2*1.5"))
        Assertions.assertEquals(7.5f, calculate("(3+2)*1.5"))
        Assertions.assertEquals(18f, calculate("2*((1+2)*3)"))
        Assertions.assertEquals(16f, calculate("20-2*(10/(7-2))"))
    }
}