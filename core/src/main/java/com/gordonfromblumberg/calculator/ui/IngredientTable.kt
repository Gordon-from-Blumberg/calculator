package com.gordonfromblumberg.calculator.ui

import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.gordonfromblumberg.calculator.Config
import com.gordonfromblumberg.calculator.model.Ingredient
import kotlin.math.roundToInt

class IngredientTable(skin: Skin) : Table(skin) {
    private val ingredients = ArrayList<Ingredient>()
    private val total = Ingredient("Total")

    init {
        top().pad(Config.edgePad)
        defaults().space(Config.cellSpace)
        buildHeader()
    }

    fun addIngredient(ingredient: Ingredient) {
        ingredients.add(ingredient)

        clearChildren()
        total.reset()

        buildHeader()
        for (ing in ingredients) {
            addIngredientRow(ing)
            add(TextButton("E", skin))
            add(TextButton("X", skin))
            total.add(ing)
        }

        addIngredientRow(total)
    }

    private fun buildHeader() {
        row().top()
        add("Name").expandX()
        add("B/100g").uniform()
        add("J/100g").uniform()
        add("U/100g").uniform()
        add("Kcal").uniform()
        add("Mass,g").uniform()
    }

    private fun addIngredientRow(ingredient: Ingredient) {
        row()
        add(ingredient.name)
        add(ingredient.proteinsPer100.round().toString())
        add(ingredient.fatsPer100.round().toString())
        add(ingredient.chsPer100.round().toString())
        add(ingredient.kcalsPer100.round().toString())
        add(ingredient.mass.toString())
    }

    private fun Float.round(): Float {
        val value = this * 10
        return value.roundToInt().toFloat() / 10
    }
}