package com.gordonfromblumberg.calculator.model

import kotlin.math.roundToInt

class Ingredient(var name: String = "",
                 var proteinsPer100: Float = 0f,
                 var fatsPer100: Float = 0f,
                 var chsPer100: Float = 0f,
                 var kcalsPer100: Float = 0f,
                 var mass: Float = 0f) {

    fun proteins() = proteinsPer100 * mass / 100
    fun fats() = fatsPer100 * mass / 100
    fun chs() = chsPer100 * mass / 100
    fun kcals() = kcalsPer100 * mass / 100

    fun proteinsPer100Rounded() = round(proteinsPer100).toString()
    fun fatsPer100Rounded() = round(fatsPer100).toString()
    fun chsPer100Rounded() = round(chsPer100).toString()
    fun kcalsPer100Rounded() = round(kcalsPer100).toString()

    fun add(other: Ingredient) {
        val newMassG = mass + other.mass
        val newMass = newMassG / 100
        proteinsPer100 = (proteins() + other.proteins()) / newMass
        fatsPer100 = (fats() + other.fats()) / newMass
        chsPer100 = (chs() + other.chs()) / newMass
        kcalsPer100 = (kcals() + other.kcals()) / newMass
        mass = newMassG
    }

    fun reset() {
        proteinsPer100 = 0f
        fatsPer100 = 0f
        chsPer100 = 0f
        kcalsPer100 = 0f
        mass = 0f
    }

    private fun round(v: Float): Float {
        val value = v * 10
        return value.roundToInt().toFloat() / 10
    }
}