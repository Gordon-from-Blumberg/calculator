package com.gordonfromblumberg.calculator.ui

import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.gordonfromblumberg.calculator.Config
import com.gordonfromblumberg.calculator.model.Ingredient

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
        add(ingredient.proteinsPer100.toString())
        add(ingredient.fatsPer100.toString())
        add(ingredient.chsPer100.toString())
        add(ingredient.kcalsPer100.toString())
        add(ingredient.mass.toString())
    }
}