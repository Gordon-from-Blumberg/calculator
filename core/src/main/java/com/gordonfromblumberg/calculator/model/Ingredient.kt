package com.gordonfromblumberg.calculator.model

import java.io.DataInputStream
import java.io.DataOutputStream
import kotlin.math.roundToInt

class Ingredient(var name: String = "",
                 var proteinsPer100: Float = 0f,
                 var fatsPer100: Float = 0f,
                 var chsPer100: Float = 0f,
                 var kcalsPer100: Float = 0f,
                 var mass: Float = 0f) : SaveLoadable {

    fun proteins(m: Float = mass) = proteinsPer100 * m / 100
    fun fats(m: Float = mass) = fatsPer100 * m / 100
    fun chs(m: Float = mass) = chsPer100 * m / 100
    fun kcals(m: Float = mass) = kcalsPer100 * m / 100

    fun proteinsPer100Rounded() = round(proteinsPer100)
    fun fatsPer100Rounded() = round(fatsPer100)
    fun chsPer100Rounded() = round(chsPer100)
    fun kcalsPer100Rounded() = round(kcalsPer100)

    fun pfcPer100() = "${proteinsPer100Rounded()} / ${fatsPer100Rounded()} / ${chsPer100Rounded()}"
    fun pfc(m: Float) = "${round(proteins(m))} / ${round(fats(m))} / ${round(chs(m))}"
    fun kcalsStr(m: Float) = round(kcals(m))

    fun add(other: Ingredient) {
        val newMassG = mass + other.mass
        val newMass = newMassG / 100
        if (newMass > 0) {
            proteinsPer100 = (proteins() + other.proteins()) / newMass
            fatsPer100 = (fats() + other.fats()) / newMass
            chsPer100 = (chs() + other.chs()) / newMass
            kcalsPer100 = (kcals() + other.kcals()) / newMass
        }
        mass = newMassG
    }

    fun reset() {
        proteinsPer100 = 0f
        fatsPer100 = 0f
        chsPer100 = 0f
        kcalsPer100 = 0f
        mass = 0f
    }

    override fun save(out: DataOutputStream) {
        out.writeUTF(name)
        out.writeFloat(proteinsPer100)
        out.writeFloat(fatsPer100)
        out.writeFloat(chsPer100)
        out.writeFloat(kcalsPer100)
        out.writeFloat(mass)
    }

    override fun load(input: DataInputStream) {
        name = input.readUTF()
        proteinsPer100 = input.readFloat()
        fatsPer100 = input.readFloat()
        chsPer100 = input.readFloat()
        kcalsPer100 = input.readFloat()
        mass = input.readFloat()
    }

    private fun round(v: Float): String {
        val value = v * 10
        return (value.roundToInt().toFloat() / 10).toString()
    }
}