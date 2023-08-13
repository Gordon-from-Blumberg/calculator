package com.gordonfromblumberg.calculator

import java.util.ArrayDeque

object Calculator {
    fun calculate(expression: String): Float {
        val stack = ArrayDeque<Operation>()
        var parsing = false
        var isDecimal = false
        var from = -1
        var value = 0f
        var expected = Type.expression
        var i = 0
        val n = expression.length
        while (i < n) {
            val ch = expression[i]
            when (ch) {
                '(' -> {
                    require(expected == Type.expression) { "Parentheses at $i not allowed" }
                    stack.addLast(Operation(Operator.openPar, 0f))
                    ++i
                    continue
                }

                ')' -> {
                    if (parsing) {
                        value = expression.substring(from, i).toFloat()
                        isDecimal = false
                        parsing = isDecimal
                    }
                    value = calculateParentheses(stack, value)
                    expected = Type.operator
                    ++i
                    continue
                }

                '.' -> {
                    require(!(isDecimal || expected == Type.operator)) { "Point at $i not allowed" }
                    isDecimal = true
                    expected = Type.number
                    if (!parsing) {
                        parsing = true
                        from = i
                    }
                    ++i
                    continue
                }

                '+', '-', '*', '/' -> {
                    if (ch == '-' && !parsing && expected != Type.operator) {
                        parsing = true
                        expected = Type.number
                        from = i
                        ++i
                        continue
                    }
                    require(expected != Type.expression) { "Unexpected operator $ch at $i" }
                    if (parsing) {
                        parsing = false
                        value = expression.substring(from, i).toFloat()
                    }
                    expected = Type.expression
                    val operation = Operation(ch, value)
                    while (!stack.isEmpty()
                            && stack.last.operator != Operator.openPar
                            && stack.last.operator.priority >= operation.operator.priority) {
                        operation.left = stack.removeLast().evaluate(operation.left)
                    }
                    stack.addLast(operation)
                    ++i
                    continue
                }
            }
            if (Character.isDigit(ch)) {
                require(expected != Type.operator) { "Unexpected digit $ch at $i" }
                if (!parsing) {
                    parsing = true
                    from = i
                    expected = Type.number
                }
            }
            ++i
        }
        if (parsing) {
            value = expression.substring(from).toFloat()
        }
        while (!stack.isEmpty()) {
            value = stack.removeLast().evaluate(value)
        }
        return value
    }

    private fun calculateParentheses(stack: ArrayDeque<Operation>, value: Float): Float {
        var result = value
        while (!stack.isEmpty()) {
            val operation = stack.removeLast()
            if (operation.operator == Operator.openPar) {
                return result
            }
            result = operation.evaluate(result)
        }
        throw IllegalStateException("Parenthesis do not match")
    }

    internal class Operation(var operator: Operator, var left: Float) {
        constructor(operator: Char, left: Float) :
                this(Operator.of(operator), left)

        fun evaluate(right: Float): Float {
            return when (operator) {
                Operator.add -> left + right
                Operator.subtract -> left - right
                Operator.multiply -> left * right
                Operator.divide -> left / right
                else -> throw IllegalStateException("Illegal operator $operator")
            }
        }
    }

    internal enum class Operator(val priority: Byte) {
        add(1.toByte()),
        multiply(2.toByte()),
        divide(2.toByte()),
        subtract(1.toByte()),
        openPar(Byte.Companion.MAX_VALUE);

        companion object {
            fun of(ch: Char): Operator {
                return when (ch) {
                    '+' -> add
                    '-' -> subtract
                    '*' -> multiply
                    '/' -> divide
                    else -> throw IllegalArgumentException("Invalid operator $ch")
                }
            }
        }
    }

    internal enum class Type {
        // '(' or number
        expression,
        number,
        operator
    }
}