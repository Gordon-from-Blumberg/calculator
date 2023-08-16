package com.gordonfromblumberg.calculator.model


class Ingredient(var proteinsPer100: Float = 0f,
                 var fatsPer100: Float = 0f,
                 var chsPer100: Float = 0f,
                 var mass: Float = 0f) {

    fun proteins() = proteinsPer100 * mass
    fun fats() = fatsPer100 * mass
    fun chs() = chsPer100 * mass
}