package com.gordonfromblumberg.calculator.ui

import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.gordonfromblumberg.calculator.Config
import com.gordonfromblumberg.calculator.Texts
import com.gordonfromblumberg.calculator.model.Ingredient

class IngredientTable(skin: Skin) : Table(skin) {
    private val ingredients = ArrayList<Ingredient>()
    val total = Ingredient(Texts.total)

    init {
        top().pad(Config.edgePad)
        defaults().space(Config.cellSpace)
        buildHeader()
    }

    fun addIngredient(ingredient: Ingredient) {
        ingredients.add(ingredient)
        rebuild()
    }

    fun rebuild() {
        clearChildren()
        total.reset()

        buildHeader()
        for (ing in ingredients) {
            addIngredientRow(ing)
            add(TextButton("E", skin)).minWidth(25f)
            add(TextButton("X", skin)).minWidth(25f)
            total.add(ing)
        }

        addIngredientRow(total)
    }

    private fun buildHeader() {
        row().top()
        add(Texts.nameCol).expandX()
        add(Texts.proteinsCol)
        add(Texts.fatsCol)
        add(Texts.carbohydratesCol)
        add(Texts.kcalCol)
        add(Texts.massCol)
    }

    private fun addIngredientRow(ingredient: Ingredient) {
        row()
        add(ingredient.name)
        add(ingredient.proteinsPer100Rounded())
        add(ingredient.fatsPer100Rounded())
        add(ingredient.chsPer100Rounded())
        add(ingredient.kcalsPer100Rounded())
        add(ingredient.mass.toString())
    }
}