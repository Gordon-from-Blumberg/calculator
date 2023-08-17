package com.gordonfromblumberg.calculator.model

class Ingredient(var name: String = "",
                 var proteinsPer100: Float = 0f,
                 var fatsPer100: Float = 0f,
                 var chsPer100: Float = 0f,
                 var kcalsPer100: Float = 0f,
                 var mass: Float = 0f) {

    fun proteins() = proteinsPer100 * mass
    fun fats() = fatsPer100 * mass
    fun chs() = chsPer100 * mass
    fun kcals() = kcalsPer100 * mass

    fun add(other: Ingredient) {
        val newMass = mass + other.mass
        proteinsPer100 = (proteins() + other.proteins()) / newMass
        fatsPer100 = (fats() + other.fats()) / newMass
        chsPer100 = (chs() + other.chs()) / newMass
        kcalsPer100 = (kcals() + other.kcals()) / newMass
        mass = newMass
    }

    fun reset() {
        proteinsPer100 = 0f
        fatsPer100 = 0f
        chsPer100 = 0f
        kcalsPer100 = 0f
        mass = 0f
    }
}